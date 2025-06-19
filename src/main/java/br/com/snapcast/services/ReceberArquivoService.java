package br.com.snapcast.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import br.com.snapcast.config.Configuracoes;
import br.com.snapcast.domain.entities.Video;
import br.com.snapcast.domain.uses_cases.ReceberVideoUseCase;
import br.com.snapcast.domain.uses_cases.ValidarVideoUseCase;
import br.com.snapcast.shared.exception.ArquivoException;
import br.com.snapcast.shared.utils.ArquivoUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

// Nao estar seguindo o clean pois nao quero trafegar em classe com objetos muitos grande 
@RequestScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
@Log
public class ReceberArquivoService {

    private Configuracoes config;
    private ReceberVideoUseCase useCase;
    private ValidarVideoUseCase validarVideoUseCase;

    public void receberArquivo(InputStream videoTemp, String nomeArquivo, long tamanho) {
        double tamanhoMB = tamanho / (1024.0 * 1024.0);
        log.info(String.format("📥 Upload iniciado - Arquivo: %s (%.2f MB)",
                nomeArquivo,
                tamanhoMB));
        validarVideoUseCase.validarVideo(nomeArquivo, tamanho);

        var diretorioData = criarDiretorio();
        var extensao = ArquivoUtil.extrairExtensao(nomeArquivo);
        var id = UUID.randomUUID().toString();
        var caminhoArquivo = diretorioData.resolve(id + "." + extensao);

        try (InputStream in = new BufferedInputStream(videoTemp)) {
            var tamanhoReal = copiarArquivo(in, caminhoArquivo);
            var video = Video.builder()
                    .id(id)
                    .nome(nomeArquivo)
                    .formatoVideo(extensao)
                    .tamanhoArquivo(tamanhoReal)
                    .caminhoArquivo(caminhoArquivo)
                    .build();
            useCase.receberVideo(video);

        } catch (IOException e) {
            throw new ArquivoException("Erro ao salvar arquivo: " + e.getMessage());
        } finally {
            fecharArquivo(videoTemp);
        }

    }

    private Path criarDiretorio() {
        try {
            var diretorioData = Path.of(config.getDiretorioVideos());
            Files.createDirectories(diretorioData);
            return diretorioData;
        } catch (IOException e) {
            throw new ArquivoException("Erro ao criar diretório: " + e.getMessage());
        }

    }

    private void fecharArquivo(InputStream video) {
        try {
            video.close();
        } catch (IOException ex) {
            throw new ArquivoException("Erro inesperado ao fechar arquivo", ex);
        }

    }

    private Long copiarArquivo(InputStream in, Path caminhoArquivo) throws IOException {
        long inicio = System.currentTimeMillis();
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(caminhoArquivo))) {
            byte[] buffer = new byte[config.getBufferSize()];
            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;

                if (totalBytes % (100 * 1024 * 1024) == 0) {
                    log.info(String.format("📊 Progresso: %s",
                            ArquivoUtil.formatFileSize(totalBytes)));
                }
            }

            long tempoTotal = System.currentTimeMillis() - inicio;
            log.info(String.format("✅ Arquivo salvo: %s (📊 %.2f MB em %d segundos)",
                    caminhoArquivo,
                    totalBytes / (1024.0 * 1024.0),
                    tempoTotal / 1000));

            return totalBytes;
        }

    }
}

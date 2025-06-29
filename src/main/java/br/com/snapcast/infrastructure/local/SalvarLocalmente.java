package br.com.snapcast.infrastructure.local;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.snapcast.port.SalvarArquivoPort;
import br.com.snapcast.shared.exception.ArquivoException;
import br.com.snapcast.shared.exception.BaseException;
import jakarta.annotation.Generated;
import lombok.extern.java.Log;

//Classe usada Somente para teste Local
@Generated("SalvarLocalmente")
@Log
public class SalvarLocalmente implements SalvarArquivoPort {

    @ConfigProperty(name = "video.storage.path", defaultValue = "/opt/snapcast/videos")
    String baseStoragePath;

    @Override
    public String salvarArquivo(Path arquivo, String nome) throws BaseException {
        try {
            Path diretorioDestino = Path.of(baseStoragePath);
            Files.createDirectories(diretorioDestino);

            String nomeArquivo = arquivo.getFileName().toString();
            Path destino = diretorioDestino.resolve(nomeArquivo);
            Files.move(arquivo, destino, StandardCopyOption.REPLACE_EXISTING);

            log.info("👌 Arquivo movido com sucesso para: " + destino);
            return destino.toString();

        } catch (IOException e) {
            throw new ArquivoException("Erro ao salvar arquivo: ", e);
        }
    }

}

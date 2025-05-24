package br.com.snapcast.domain.uses_cases;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import br.com.snapcast.domain.entities.Video;
import br.com.snapcast.port.SalvarArquivoPort;
import br.com.snapcast.shared.exception.ArquivoException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
@Log
public class ReceberVideoUseCase {

    SalvarArquivoPort port;
    private static final int MAX_RETRIES = 2;
    private static final long RETRY_DELAY_MS = 1000;

    public void receberVideo(Video video) {

        CompletableFuture.runAsync(() -> salvarArquivoComRetry(video.getCaminhoArquivo()));

    }

    private void salvarArquivoComRetry(Path caminhoArquivo) {
        int tentativas = 0;
        while (tentativas <= MAX_RETRIES) {
            try {
                port.salvarArquivo(caminhoArquivo);
                log.info("👌 Arquivo salvo com sucesso na tentativa " + (tentativas + 1));
                return;
            } catch (Exception e) {
                tentativas++;
                if (tentativas > MAX_RETRIES) {
                    log.severe("Falha ao salvar arquivo após " + MAX_RETRIES + " tentativas. Erro: " + e.getMessage());
                    throw e;
                }
                log.warning("Tentativa " + tentativas + " falhou. Tentando novamente em 1 segundo...");
                criandoUmDelay();
            }
        }
    }

    private void criandoUmDelay() {
        try {
            Thread.sleep(RETRY_DELAY_MS);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new ArquivoException("Operação interrompida", ie);
        }
    }

}

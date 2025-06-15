package br.com.snapcast.domain.uses_cases;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import org.eclipse.microprofile.faulttolerance.Retry;

import br.com.snapcast.domain.entities.Video;
import br.com.snapcast.domain.entities.VideoEvento;
import br.com.snapcast.infrastructure.event.producer.VideoProducer;
import br.com.snapcast.port.SalvarArquivoPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
@Log
public class ReceberVideoUseCase {

    SalvarArquivoPort port;
    VideoProducer producer;

    public void receberVideo(Video video) {

        CompletableFuture.runAsync(() -> {
            var novoCaminho = salvarArquivo(video.getCaminhoArquivo(), video);
            producer.emitirVideoCriado(new VideoEvento(video, novoCaminho));
        });

    }

    @Retry(maxRetries = 4)
    public String salvarArquivo(Path caminhoArquivo, Video video) {
        return port.salvarArquivo(caminhoArquivo, video.nomeArquivo());
    }

}

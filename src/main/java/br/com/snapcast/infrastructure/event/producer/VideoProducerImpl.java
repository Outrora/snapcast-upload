package br.com.snapcast.infrastructure.event.producer;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.snapcast.domain.entities.VideoEvento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class VideoProducerImpl implements VideoProducer {

    Emitter<VideoEvento> emitter;

    @Inject
    public VideoProducerImpl(
            @Channel("video-uploads") Emitter<VideoEvento> emitter) {
        this.emitter = emitter;
    }

    @Override
    @Retry(maxRetries = 4, delay = 10)
    public void emitirVideoCriado(VideoEvento evento) {
        log.info("✈️ Mandando para a fila o video: %s".formatted(evento.nome()));

        emitter.send(evento)
                .thenRun(() -> log.info("✈️ Enviado"))
                .toCompletableFuture()
                .join();

    }

}

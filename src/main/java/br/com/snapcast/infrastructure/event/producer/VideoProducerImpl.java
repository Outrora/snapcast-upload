package br.com.snapcast.infrastructure.event.producer;

import java.util.concurrent.CompletionException;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.snapcast.domain.entities.VideoEvento;
import br.com.snapcast.shared.exception.EventoNaoEnviado;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class VideoProducerImpl implements VideoProducer {

    Emitter<VideoEvento> emitter;

    @Inject
    VideoProducerImpl(
            @Channel("video-uploads") Emitter<VideoEvento> emitter) {
        this.emitter = emitter;
    }

    @Override
    public void emitirVideoCriado(VideoEvento evento) {
        log.info("✈️ Mandando para a fila o video: %s".formatted(evento.nome()));

        try {
            emitter.send(evento)
                    .thenRun(() -> log.info("✈️ Enviado"))
                    .toCompletableFuture()
                    .join();
        } catch (CompletionException ex) {
            throw new EventoNaoEnviado();
        }
    }

}

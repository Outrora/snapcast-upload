package br.com.snapcast.event.producer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.snapcast.CriarObjeto.CriarVideo;
import br.com.snapcast.domain.entities.VideoEvento;
import br.com.snapcast.infrastructure.event.producer.VideoProducer;
import br.com.snapcast.infrastructure.event.producer.VideoProducerImpl;

class VideoProducerTest {

    @Mock
    Emitter<VideoEvento> emitter;

    VideoProducer producer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        producer = new VideoProducerImpl(emitter);
    }

    @Test
    void testarQueEmitirVIdeoCriadoEMandadoCorretamente() {
        var video = new VideoEvento(CriarVideo.criar());

        when(emitter.send(any(VideoEvento.class)))
                .thenReturn(CompletableFuture.completedFuture(null));
        producer.emitirVideoCriado(video);

        verify(emitter, times(1)).send(video);

    }

}

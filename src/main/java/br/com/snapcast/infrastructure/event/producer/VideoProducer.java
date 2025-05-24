package br.com.snapcast.infrastructure.event.producer;

import br.com.snapcast.domain.entities.VideoEvento;

public interface VideoProducer {

    void emitirVideoCriado(VideoEvento evento);
}

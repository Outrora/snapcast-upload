package br.com.snapcast.shared.exception;

import jakarta.ws.rs.core.Response.Status;

public class EventoNaoEnviado extends BaseException {

    public EventoNaoEnviado() {
        super(Status.INTERNAL_SERVER_ERROR, "Erro Ao enviar para a fila");
    }
}

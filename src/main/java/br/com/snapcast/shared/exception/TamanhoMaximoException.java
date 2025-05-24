package br.com.snapcast.shared.exception;

import jakarta.ws.rs.core.Response.Status;

public class TamanhoMaximoException extends BaseException {

    public TamanhoMaximoException() {
        super(Status.BAD_REQUEST, "Tentativa de upload de arquivo maior que o permitido");
    }

}

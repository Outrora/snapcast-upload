package br.com.snapcast.shared.exception;

import jakarta.ws.rs.core.Response.Status;

public class FormatoInvalidoException extends BaseException {

    public FormatoInvalidoException() {
        super(Status.BAD_REQUEST, "Formato do Arquivo Invalido");
    }

}

package br.com.snapcast.shared.exception;

import jakarta.ws.rs.core.Response.Status;

public class ArgumentosInvalidosException extends BaseException {

    public ArgumentosInvalidosException() {
        super(Status.BAD_REQUEST, "Argumentos Inválidos");
    }

}

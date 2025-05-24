package br.com.snapcast.shared.exception;

import jakarta.ws.rs.core.Response.Status;

public class ArquivoException extends BaseException {

    public ArquivoException() {
        super(Status.INTERNAL_SERVER_ERROR, "Erro ao processar arquivo de vídeo");
    }

    public ArquivoException(String mensagem) {
        super(Status.INTERNAL_SERVER_ERROR, mensagem);
    }

    public ArquivoException(String mensagem, Throwable causa) {
        super(Status.INTERNAL_SERVER_ERROR, mensagem, causa);
    }
}

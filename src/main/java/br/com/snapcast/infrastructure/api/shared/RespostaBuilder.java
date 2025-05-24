package br.com.snapcast.infrastructure.api.shared;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.StatusType;

public abstract class RespostaBuilder {

    private RespostaBuilder() {
    }

    public static Response criarResposta(StatusType status, String mensagem) {
        var resposta = new MensagemResposta(mensagem, LocalDateTime.now(), status.getStatusCode());

        return Response
                .status(status)
                .entity(resposta)
                .build();
    }

}

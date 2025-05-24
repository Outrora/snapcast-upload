package br.com.snapcast.infrastructure.api.shared;

import java.time.LocalDateTime;

public record MensagemResposta(String mensagem, LocalDateTime timestamp, Integer code) {
}
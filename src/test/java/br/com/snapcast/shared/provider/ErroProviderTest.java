package br.com.snapcast.shared.provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.snapcast.shared.exception.ArquivoException;
import jakarta.ws.rs.core.Response.Status;

class ErroProviderTest {

    ErroProvider provider;

    @BeforeEach
    void setup() {
        provider = new ErroProvider();
    }

    @Test
    void TestarSeAoReceberArquivoException() {
        var exception = new ArquivoException();

        var resposta = provider.toResponse(exception);

        assertThat(resposta.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR.getStatusCode());
        assertThat(resposta.getEntity()).isInstanceOf(RespostaErro.class);
    }

    @Test
    void TestarSeAoReceberOutroErroQualquer() {
        var exception = new Exception();

        var resposta = provider.toResponse(exception);

        assertThat(resposta.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR.getStatusCode());
        assertThat(resposta.getEntity()).isInstanceOf(RespostaErro.class);
    }

}

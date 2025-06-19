package br.com.snapcast.infrastructure.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.snapcast.infrastructure.api.shared.MensagemResposta;
import br.com.snapcast.services.ReceberArquivoService;
import jakarta.ws.rs.core.Response.Status;

class EndPointReceberArquivoTest {

    @Mock
    ReceberArquivoService port;

    EndPointReceberArquivo endPoint;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        endPoint = new EndPointReceberArquivo(port);
    }

    @Test
    void testarSeEndPointReceberArquivoFuncionaCorretamente() {

        InputStream is = getClass().getResourceAsStream("/test-video.mp4");
        String nome = "test-video.mp4";
        Long tamanho = 23854558L;

        doNothing().when(port).receberArquivo(any(), anyString(), anyLong());

        var reposta = endPoint.uploadVideo(is, nome, tamanho);

        verify(port, times(1)).receberArquivo(is, nome, tamanho);

        assertThat(reposta.getStatus()).isEqualTo(Status.ACCEPTED.getStatusCode());
        assertThat(reposta.getEntity()).isInstanceOf(MensagemResposta.class);

    }

}

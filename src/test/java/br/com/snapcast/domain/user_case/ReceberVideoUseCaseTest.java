package br.com.snapcast.domain.user_case;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.awaitility.Awaitility.await;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.snapcast.CriarObjeto.CriarVideo;
import br.com.snapcast.domain.uses_cases.ReceberVideoUseCase;
import br.com.snapcast.infrastructure.event.producer.VideoProducer;
import br.com.snapcast.port.SalvarArquivoPort;
import br.com.snapcast.shared.exception.ArgumentosInvalidosException;

class ReceberVideoUseCaseTest {

    @Mock
    SalvarArquivoPort port;

    @Mock
    VideoProducer producer;

    ReceberVideoUseCase useCase;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
        useCase = new ReceberVideoUseCase(port, producer);

    }

    @Test
    void testarSeSalvarArquivoFuncionaCorretamente() {

        var video = CriarVideo.criar();

        when(port.salvarArquivo(any(), any())).thenReturn("Salvo");

        var retorno = useCase.salvarArquivo(video.getCaminhoArquivo(), video);

        verify(port, times(1)).salvarArquivo(any(), any());
        assertThat(retorno).isNotBlank().isNotEmpty().contains("Salvo");

    }

    @Test
    void testarSeSalvarArquivoFuncionaRetornaOErroCorretamente() {

        var video = CriarVideo.criar();

        when(port.salvarArquivo(any(), any())).thenThrow(new ArgumentosInvalidosException());

        assertThatThrownBy(() -> {
            useCase.salvarArquivo(video.getCaminhoArquivo(), video);
        })
                .isInstanceOf(ArgumentosInvalidosException.class)
                .hasMessageContaining("Argumentos Inválidos");

        verify(port, times(1)).salvarArquivo(any(), any());

    }

    @Test
    void testarSeAoReceberArquivoFuncionaCorretamente() {
        var video = CriarVideo.criar();

        when(port.salvarArquivo(any(), any())).thenReturn("Salvo");

        doNothing().when(producer).emitirVideoCriado(any());

        await().atMost(5, TimeUnit.SECONDS).until(() -> {
            useCase.receberVideo(video);
            return true;
        });

        verify(port, times(1)).salvarArquivo(any(), any());
        verify(producer, times(1)).emitirVideoCriado(any());
    }

}

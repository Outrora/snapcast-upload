package br.com.snapcast.domain.user_case;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.snapcast.domain.uses_cases.ValidarVideoUseCase;
import br.com.snapcast.shared.exception.ArgumentosInvalidosException;
import br.com.snapcast.shared.exception.ArquivoException;
import br.com.snapcast.shared.exception.FormatoInvalidoException;
import br.com.snapcast.shared.exception.TamanhoMaximoException;

class ValidarVideoUseCaseTest {

    ValidarVideoUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new ValidarVideoUseCase();
    }

    @Test
    void testarSeValidadaCorretamente() {

        var nome = "video.mp4";
        var tamanho = 5000L * 1024L;

        useCase.validarVideo(nome, tamanho);

        assertThatNoException();

    }

    @Test
    void testarSeValidaNomeArquivo() {

        var tamanho = 5000L * 1024L;

        assertThatThrownBy(() -> {
            useCase.validarVideo(null, tamanho);
        })
                .isInstanceOf(ArgumentosInvalidosException.class)
                .hasMessageContaining("Argumentos Inválidos");

    }

    @Test
    void testarSeValidaNomeArquivoVazio() {

        var tamanho = 5000L * 1024L;

        assertThatThrownBy(() -> {
            useCase.validarVideo("", tamanho);
        })
                .isInstanceOf(ArgumentosInvalidosException.class)
                .hasMessageContaining("Argumentos Inválidos");

    }

    @Test
    void testarSeValidaTamanhoArquivo() {

        var nome = "video.mp4";
        var tamanho = 6_000L * 1_024L * 1_024L;

        assertThatThrownBy(() -> {
            useCase.validarVideo(nome, tamanho);
        })
                .isInstanceOf(TamanhoMaximoException.class)
                .hasMessageContaining("Tentativa de upload de arquivo maior que o permitido");

    }

    @Test
    void testarSeValidaTipoArquivo() {

        var nome = "video.txt";
        var tamanho = 2_000L * 1_024L * 1_024L;

        assertThatThrownBy(() -> {
            useCase.validarVideo(nome, tamanho);
        })
                .isInstanceOf(FormatoInvalidoException.class)
                .hasMessageContaining("Formato do Arquivo Invalido:")
                .hasMessageContaining("txt");

    }

}

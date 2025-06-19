
package br.com.snapcast.services;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.snapcast.config.Configuracoes;
import br.com.snapcast.domain.uses_cases.ReceberVideoUseCase;
import br.com.snapcast.domain.uses_cases.ValidarVideoUseCase;

public class ReceberArquivoServiceTest {

    @Mock
    Configuracoes config;

    @Mock
    ReceberVideoUseCase useCase;

    @Mock
    ValidarVideoUseCase validarVideoUseCase;

    ReceberArquivoService service;

    @TempDir
    Path localTemporario;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ReceberArquivoService(config, useCase, validarVideoUseCase);
    }

    @Test
    void testarSeReceberArquivoFunciona() {

        InputStream is = getClass().getResourceAsStream("/test-video.mp4");
        String nome = "test-video.mp4";
        Long tamanho = 23854558L;

        doNothing().when(useCase).receberVideo(any());
        doNothing().when(validarVideoUseCase).validarVideo(anyString(), anyLong());

        when(config.getDiretorioVideos()).thenReturn(localTemporario.toString());
        when(config.getBufferSize()).thenReturn(8192);
        service.receberArquivo(is, nome, tamanho);

        assertThatNoException();

    }

}
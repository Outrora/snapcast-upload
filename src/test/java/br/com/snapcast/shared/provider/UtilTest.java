package br.com.snapcast.shared.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import br.com.snapcast.shared.utils.ArquivoUtil;

public class UtilTest {

    @Test
    void testParaFormatado() {

        Long tamanho = 5_000L * 1_024L * 1_024L; // 5GB;
        Long tamanho2 = 500L * 1_024L * 1_024L; // 5MB;
        var teste1 = ArquivoUtil.formatFileSize(tamanho);
        var teste2 = ArquivoUtil.formatFileSize(tamanho2);

        assertThat(teste1).contains("GB");
        assertThat(teste2).contains("MB");
    }

    @Test
    void testParaExtraiExtensaoERetornaErro() {

        assertThatThrownBy(() -> {
            ArquivoUtil.extrairExtensao("fsdfsdf");
        }).hasMessageContaining("Nome de arquivo inválido");
    }

}

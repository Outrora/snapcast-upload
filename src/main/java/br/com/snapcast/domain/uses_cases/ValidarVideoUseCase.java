package br.com.snapcast.domain.uses_cases;

import java.util.Set;

import br.com.snapcast.shared.exception.ArgumentosInvalidosException;
import br.com.snapcast.shared.exception.ArquivoException;
import br.com.snapcast.shared.exception.TamanhoMaximoException;
import br.com.snapcast.shared.utils.ArquivoUtil;
import jakarta.enterprise.context.ApplicationScoped;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class ValidarVideoUseCase {
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("mp4", "avi", "mkv", "mov");
    public static final long TAMANHO_MAXIMO_BYTES = 5_000L * 1_024L * 1_024L; // 5GB

    public void validarVideo(String nomeArquivo, long tamanho) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            throw new ArgumentosInvalidosException();
        }

        if (tamanho > TAMANHO_MAXIMO_BYTES) {
            throw new TamanhoMaximoException();
        }

        var extensao = ArquivoUtil.extrairExtensao(nomeArquivo);
        if (!ALLOWED_EXTENSIONS.contains(extensao.toLowerCase())) {
            throw new ArquivoException("Extensão de arquivo não permitida: " + extensao);
        }
    }

}

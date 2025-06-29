package br.com.snapcast.shared.utils;

import java.util.Optional;

import br.com.snapcast.shared.exception.ArquivoException;

public interface ArquivoUtil {

    public static String extrairExtensao(String nomeArquivo) {
        return Optional.ofNullable(nomeArquivo)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1))
                .orElseThrow(() -> new ArquivoException("Nome de arquivo inválido"));
    }

    public static String formatFileSize(long bytes) {
        String[] units = { "B", "KB", "MB", "GB" };
        int unitIndex = 0;
        double size = bytes;

        while (size > 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", size, units[unitIndex]);
    }
}

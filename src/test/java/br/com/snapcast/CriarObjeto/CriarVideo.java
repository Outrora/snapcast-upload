package br.com.snapcast.CriarObjeto;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import br.com.snapcast.domain.entities.Video;

public interface CriarVideo {

    static Random random = new Random();
    static List<String> formas = List.of("mp4", "avi");

    static Video criar() {
        return Video.builder()
                .id(UUID.randomUUID().toString())
                .nome("video")
                .formatoVideo(formas.get(random.nextInt(formas.size())))
                .tamanhoArquivo(random.nextLong())
                .caminhoArquivo(Path.of("/temp/"))
                .build();

    }
}

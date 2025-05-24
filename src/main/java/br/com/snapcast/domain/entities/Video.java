package br.com.snapcast.domain.entities;

import java.nio.file.Path;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Video {

    String nome;
    String formatoVideo;
    Long tamanhoArquivo;
    Path caminhoArquivo;

}

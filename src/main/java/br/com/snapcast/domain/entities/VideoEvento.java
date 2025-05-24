package br.com.snapcast.domain.entities;

public record VideoEvento(String nome,
        String formatoVideo,
        Long tamanhoVideo,
        String caminhoVideo) {

    public VideoEvento(Video video) {
        this(video.nome, video.formatoVideo, video.tamanhoArquivo, video.caminhoArquivo.toString());
    }

}

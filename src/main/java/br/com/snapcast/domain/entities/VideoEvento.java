package br.com.snapcast.domain.entities;

public record VideoEvento(
        String id,
        String nome,
        String formatoVideo,
        Long tamanhoVideo,
        String caminhoVideo) {

    public VideoEvento(Video video) {
        this(video.nome, video.nome, video.formatoVideo, video.tamanhoArquivo, video.caminhoArquivo.toString());
    }

    public VideoEvento(Video video, String novoCaminho) {
        this(video.nome, video.nome, video.formatoVideo, video.tamanhoArquivo, novoCaminho);
    }

}

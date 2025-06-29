package br.com.snapcast.domain.entities;

public record VideoEvento(
        String id,
        String nome,
        String formatoVideo,
        Long tamanhoVideo,
        String caminhoVideo,
        String idUsuario

) {

    public VideoEvento(Video video) {
        this(video.id, video.nome, video.formatoVideo, video.tamanhoArquivo, video.caminhoArquivo.toString(),
                video.idUsuario);
    }

    public VideoEvento(Video video, String novoCaminho) {
        this(video.id, video.nome, video.formatoVideo, video.tamanhoArquivo, novoCaminho, video.idUsuario);
    }

}

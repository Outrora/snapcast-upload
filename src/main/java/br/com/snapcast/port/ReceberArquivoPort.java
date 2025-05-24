package br.com.snapcast.port;

import java.io.InputStream;

public interface ReceberArquivoPort {

    void receberArquivo(InputStream video, String nomeArquivo, long tamanho);

}

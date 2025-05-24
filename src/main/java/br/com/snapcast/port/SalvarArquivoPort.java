package br.com.snapcast.port;

import java.nio.file.Path;

import br.com.snapcast.shared.exception.BaseException;

public interface SalvarArquivoPort {

    void salvarArquivo(Path file) throws BaseException;

}
package br.com.snapcast.infrastructure.s3;

import java.nio.file.Path;

import br.com.snapcast.config.ClienteS3;
import br.com.snapcast.port.SalvarArquivoPort;
import br.com.snapcast.shared.exception.BaseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@ApplicationScoped
@AllArgsConstructor(onConstructor = @__(@Inject))
@Log
public class SalvarS3 implements SalvarArquivoPort {

        ClienteS3 s3;

        @Override
        public String salvarArquivo(Path file, String nome) throws BaseException {
                log.info("🚀 Enviando Arquivo para: S3 ");
                s3.pegarS3().putObject(
                                PutObjectRequest.builder()
                                                .bucket(s3.getBucket())
                                                .key(nome)
                                                .contentType("application/octet-stream")
                                                .build(),
                                file);

                log.info("👌 Arquivo movido com sucesso para S3 ");
                return "https://%s.s3.%s.amazonaws.com/%s".formatted(s3.getBucket(), s3.getRegiao(), nome);

        }

}

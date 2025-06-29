package br.com.snapcast.infrastructure.s3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.snapcast.config.ClienteS3;
import br.com.snapcast.port.SalvarArquivoPort;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

class SalvarS3Test {

    @Mock
    ClienteS3 s3;

    @Mock
    S3Client client;

    @TempDir
    Path temporario;

    SalvarArquivoPort arquivoS3;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        arquivoS3 = new SalvarS3(s3);
    }

    @Test
    void verificarSeChamouOClienteS3Corretamente() {

        var caminho = Path.of("/temp/path");
        var bucket = "Bucket1";
        var regiao = "Regiao";

        when(s3.pegarS3()).thenReturn(client);
        when(s3.getBucket()).thenReturn(bucket);
        when(s3.getRegiao()).thenReturn(regiao);

        when(client.putObject(any(PutObjectRequest.class), any(Path.class)))
                .thenReturn(null);

        var retorno = arquivoS3.salvarArquivo(caminho, "test");

        assertThat(retorno)
                .contains(bucket)
                .contains(regiao)
                .contains("amazonaws");

    }

}

package br.com.snapcast.infrastructure.api;

import static br.com.snapcast.infrastructure.api.shared.RespostaBuilder.criarResposta;
import java.io.InputStream;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.snapcast.services.ReceberArquivoService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Path("video")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor(onConstructor = @__(@Inject))
@Log
@Tag(name = "Video", description = "Endpoints para gerenciamento de vídeos")
public class EndPointReceberArquivo {

    private final ReceberArquivoService port;
    JsonWebToken jwt;

    @POST()
    @Path("upload")
    @RunOnVirtualThread()
    @Authenticated
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(summary = "Realizar upload de vídeo", description = "Realiza o upload de um arquivo de vídeo")
    public Response uploadVideo(
            @NotNull() @Parameter(description = "Stream do arquivo de vídeo") InputStream video,
            @Parameter(description = "Nome do arquivo de vídeo") @HeaderParam("nomeArquivo") String nomeArquivo,
            @Parameter(description = "Tamanho do arquivo em bytes") @HeaderParam("Content-Length") long fileSize) {

        String id = jwt.getClaim("username");

        port.receberArquivo(video, nomeArquivo, fileSize, id);

        return criarResposta(Status.ACCEPTED, "Upload realizado com sucesso");
    }

}

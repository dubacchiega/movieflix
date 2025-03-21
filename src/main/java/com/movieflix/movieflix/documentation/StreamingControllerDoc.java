package com.movieflix.movieflix.documentation;


import com.movieflix.movieflix.streaming.entity.Streaming;
import com.movieflix.movieflix.streaming.mapper.StreamingMapper;
import com.movieflix.movieflix.streaming.request.StreamingRequest;
import com.movieflix.movieflix.streaming.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Streaming", description = "Recurso responsável por realizar o gerenciamento de streamings.")
public interface StreamingControllerDoc {

    @GetMapping()
    @Operation(summary = "Buscar todos os streamings.", description = "Este método é responsável por buscar todos os streamings cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streamings encontrados com sucesso.",
        content = @Content(array =@ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    public ResponseEntity<List<StreamingResponse>> getAllStreamings();

    @PostMapping()
    @Operation(summary = "Salvar streaming.", description = "Este método é responsável por salvar um streaming.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Streaming salvo com sucesso.",
        content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request);

    @GetMapping("/{id}")
    @Operation(summary = "Buscar streaming por id.", description = "Este método é responsável por buscar um streaming por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Streaming encontrado com sucesso.",
        content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar streaming por id.", description = "Este método é responsável por deletar um streaming por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso.", content = @Content())
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id);
}

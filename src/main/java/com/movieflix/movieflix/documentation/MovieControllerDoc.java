package com.movieflix.movieflix.documentation;

import com.movieflix.movieflix.movie.request.MovieRequest;
import com.movieflix.movieflix.movie.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO terminar de implementar a documentação para todos os controllers, requests e responses.

@Tag(name="Movie", description = "Recurso responsável por realizar o gerenciamento de filmes.")
public interface MovieControllerDoc {

    @Operation(summary = "Salvar filme.", description = "Este método é responsável por salvar um filme.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping
    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request);



    @Operation(summary = "Buscar todos os filmes.", description = "Este método é responsável por buscar todos os filmes cadastrado.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrados com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado.", content = @Content())
    @GetMapping
    ResponseEntity<List<MovieResponse>> findAll();



    @Operation(summary = "Buscar filme por id.", description = "Este método é responsável por buscar um filme por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @GetMapping("/{id}")
    ResponseEntity<MovieResponse> findById(@PathVariable Long id);


    @Operation(summary = "Atualizar filme.", description = "Este método é responsável por alterar dados de um filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado.", content = @Content())
    @PutMapping("/{id}")
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request);


    @Operation(summary = "Buscar filme por categoria.", description = "Este método é responsável por buscar filmes por categoria.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping("/search")
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    @Operation(summary = "Deletar filme por id.", description = "Este método é responsável por deletar um filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso.", content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado.", content = @Content())
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}

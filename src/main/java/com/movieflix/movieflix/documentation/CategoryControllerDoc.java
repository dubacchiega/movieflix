package com.movieflix.movieflix.documentation;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.category.mapper.CategoryMapper;
import com.movieflix.movieflix.category.request.CategoryRequest;
import com.movieflix.movieflix.category.response.CategoryResponse;
import com.movieflix.movieflix.movie.response.MovieResponse;
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

@Tag(name="Category", description = "Recurso responsável por realizar o gerenciamento de categorias.")
public interface CategoryControllerDoc {

    @GetMapping()
    @Operation(summary = "Buscar todas as categorias.", description = "Este método é responsável por buscar todas as categorias cadastradas",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categorias encontrados com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrado.", content = @Content())
    public ResponseEntity<List<CategoryResponse>> getAllCategories();


    @Operation(summary = "Salvar categoria.", description = "Este método é responsável por salvar uma categoria.")
    @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso.",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request);



    @Operation(summary = "Buscar categoria por id.", description = "Este método é responsável por buscar uma categoria por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso.",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada.", content = @Content())
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id);


    @Operation(summary = "Deletar categoria por id.", description = "Este método é responsável por deletar uma categoria por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
@ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso.", content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id);
}

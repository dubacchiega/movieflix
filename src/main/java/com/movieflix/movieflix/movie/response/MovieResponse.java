package com.movieflix.movieflix.movie.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.movieflix.category.response.CategoryResponse;
import com.movieflix.movieflix.streaming.response.StreamingResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(@Schema(type = "long", description = "Código do filme")
                            Long id,
                            @Schema(type = "string", description = "Nome do filme")
                            String title,
                            @Schema(type = "string", description = "Descrição do filme")
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            @Schema(type = "date", description = "Data de lançamento do filme")
                            LocalDate releaseDate,
                            @Schema(type = "double", description = "Avaliação do filme")
                            double rating,
                            @Schema(type = "array", description = "Lista de categorias")
                            List<CategoryResponse> categories,
                            @Schema(type = "array", description = "Lista de streamings")
                            List<StreamingResponse> streamings) {
}

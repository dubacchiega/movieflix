package com.movieflix.movieflix.movie.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.movieflix.category.response.CategoryResponse;
import com.movieflix.movieflix.streaming.response.StreamingResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,

                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,
                            double rating,
                            List<CategoryResponse> categories,
                            List<StreamingResponse> streamings) {
}

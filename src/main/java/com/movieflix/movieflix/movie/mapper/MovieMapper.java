package com.movieflix.movieflix.movie.mapper;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.category.mapper.CategoryMapper;
import com.movieflix.movieflix.category.response.CategoryResponse;
import com.movieflix.movieflix.movie.entity.Movie;
import com.movieflix.movieflix.movie.request.MovieRequest;
import com.movieflix.movieflix.movie.response.MovieResponse;
import com.movieflix.movieflix.streaming.entity.Streaming;
import com.movieflix.movieflix.streaming.mapper.StreamingMapper;
import com.movieflix.movieflix.streaming.response.StreamingResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){

        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build()) //O Hibernate entende que você está referenciando uma entidade já existente no banco.
                .toList();


        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}

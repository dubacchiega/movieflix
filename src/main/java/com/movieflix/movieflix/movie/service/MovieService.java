package com.movieflix.movieflix.movie.service;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.category.service.CategoryService;
import com.movieflix.movieflix.movie.entity.Movie;
import com.movieflix.movieflix.movie.repository.MovieRepository;
import com.movieflix.movieflix.movie.response.MovieResponse;
import com.movieflix.movieflix.streaming.entity.Streaming;
import com.movieflix.movieflix.streaming.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public List<Movie> findByCategory(Long categoryId){
        // construo um category através do id recebido e mando essa category para ser buscada no meu repository
        // eu só preciso passar o id pois o JPA vai fazer a consulta atravé do Id pois o Id é minha chave primaria
        // uma alternativa é eu chamar o categoryRepository e dar o find pelo id da category e depois disso passar para dentro da minha query de achar o movie pela categories
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie){
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()){
            final List<Category> categories = this.findCategories(updateMovie.getCategories());
            final List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());
            
            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }


}

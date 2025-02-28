package com.movieflix.movieflix.movie.controller;

import com.movieflix.movieflix.movie.entity.Movie;
import com.movieflix.movieflix.movie.mapper.MovieMapper;
import com.movieflix.movieflix.movie.request.MovieRequest;
import com.movieflix.movieflix.movie.response.MovieResponse;
import com.movieflix.movieflix.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList()
        );
    }
}

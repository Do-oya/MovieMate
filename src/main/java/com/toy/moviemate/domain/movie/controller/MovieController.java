package com.toy.moviemate.domain.movie.controller;

import com.toy.moviemate.domain.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String getPopularMovies(Model model) {
        Map<String, Object> movies = movieService.getPopularMovies();
        model.addAttribute("movies", movies.get("results"));
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable("id") String movieId, Model model) {
        Map<String, Object> movieDetails = movieService.getMovieDetails(movieId);
        model.addAttribute("movie", movieDetails);
        return "movie-details";
    }
}
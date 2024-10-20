package com.toy.moviemate.domain.movie.controller;

import com.toy.moviemate.domain.movie.service.MovieService;
import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
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
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("movie", movieDetails);
        model.addAttribute("reviews", reviews);
        return "movie-details";
    }
}
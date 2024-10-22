package com.toy.moviemate.domain.movie.controller;

import com.toy.moviemate.domain.movie.service.MovieService;
import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final ReviewQueryService reviewQueryService;

    public MovieController(MovieService movieService, ReviewQueryService reviewQueryService) {
        this.movieService = movieService;
        this.reviewQueryService = reviewQueryService;
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
        List<ReviewDto> reviews = reviewQueryService.getReviewsByMovieId(movieId);
        model.addAttribute("movie", movieDetails);
        model.addAttribute("reviews", reviews);
        return "movie-details";
    }
}
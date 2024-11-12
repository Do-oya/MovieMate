package com.toy.moviemate.domain.movie.controller;

import com.toy.moviemate.domain.movie.service.impl.MovieServiceImpl;
import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;
    private final ReviewService reviewService;

    @GetMapping("/movies/home")
    public String home(Model model) {
        return "movie/home";
    }

    @GetMapping("/movies/popular")
    public String getPopularMovies(Model model) {
        model.addAttribute("movies", movieService.getPopularMovies().get("results"));
        return "movie/movie-popular";
    }

    @GetMapping("/movies/latest")
    public String getLatestMovies(Model model) {
        model.addAttribute("movies", movieService.getLatestMovies().get("results"));
        return "movie/movie-latest";
    }

    @GetMapping("/movies/now-playing")
    public String getNowPlayingMovies(Model model) {
        model.addAttribute("movies", movieService.getNowPlayingMovies().get("results"));
        return "movie/movie-now-playing";
    }

    @GetMapping("/movies/upcoming")
    public String getUpcomingMovies(Model model) {
        model.addAttribute("movies", movieService.getUpcomingMovies().get("results"));
        return "movie/movie-upcoming";
    }

    @GetMapping("/movies/korean")
    public String getKoreanMovies(Model model) {
        model.addAttribute("movies", movieService.getKoreanMovies().get("results"));
        return "movie/movie-korean";
    }

    @GetMapping("/movies/foreign")
    public String getForeignMovies(Model model) {
        model.addAttribute("movies", movieService.getForeignMovies().get("results"));
        return "movie/movie-foreign";
    }

    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable("id") String movieId, Model model) {
        Map<String, Object> movieDetails = movieService.getMovieDetails(movieId);
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("movie", movieDetails);
        model.addAttribute("reviews", reviews);
        return "movie/movie-details";
    }
}
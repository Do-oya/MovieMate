package com.toy.moviemate.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Slf4j
@Controller
public class MovieApiController {

    private final MovieApiService movieApiService;

    public MovieApiController(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    // 영화 목록 페이지
    @GetMapping("/movies")
    public String getPopularMovies(Model model) {
        Map<String, Object> moviesData = movieApiService.getPopularMovies();
        model.addAttribute("movies", moviesData.get("results"));
        log.info("movies data: {}", moviesData);
        return "movies";
    }

    // 영화 세부 정보 페이지
    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable("id") String movieId, Model model) {
        Map<String, Object> movieDetails = movieApiService.getMovieDetails(movieId);
        model.addAttribute("movie", movieDetails);
        return "movie-details";
    }
}

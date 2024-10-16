package com.toy.moviemate.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Slf4j
@Controller
public class MovieApiController {

    private final MovieApiService movieApiService;

    public MovieApiController(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    @GetMapping("/movies")
    public String getPopularMovies(Model model) {
        Map<String, Object> moviesData = movieApiService.getPopularMovies();
        model.addAttribute("movies", moviesData.get("results"));
        log.info("movies data: {}", moviesData);
        return "movies";
    }
}

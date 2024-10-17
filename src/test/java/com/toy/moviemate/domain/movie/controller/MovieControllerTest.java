package com.toy.moviemate.domain.movie.controller;

import com.toy.moviemate.domain.movie.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    @DisplayName("영화 목록 페이지가 정상적으로 반환되어야 함")
    void testGetPopularMovies() throws Exception {
        // given
        Map<String, Object> fakeResponse = Map.of("results", List.of(Map.of("title", "Test Movie")));
        when(movieService.getPopularMovies()).thenReturn(fakeResponse);

        // when & then
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attribute("movies", fakeResponse.get("results")));
    }

    @Test
    @DisplayName("영화 세부 정보 페이지가 정상적으로 반환되어야 한다")
    void testGetMovieDetails() throws Exception {
        // given
        String movieId = "12345";
        Map<String, Object> fakeMovieDetails = Map.of("title", "Test Movie", "id", movieId);
        when(movieService.getMovieDetails(anyString())).thenReturn(fakeMovieDetails);

        // when & then
        mockMvc.perform(get("/movies/{id}", movieId))
                .andExpect(status().isOk())
                .andExpect(view().name("movie-details"))
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attribute("movie", fakeMovieDetails));
    }
}

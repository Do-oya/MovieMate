package com.toy.moviemate.domain.movie.service;

import com.toy.moviemate.api.tmdb.service.TmdbApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private TmdbApiService tmdbApiService;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("TMDB API에서 인기 영화를 가져옴")
    void testGetPopularMovies() {
        // given
        Map<String, Object> fakeResponse = Map.of("results", List.of(Map.of("title", "Test Movie")));
        when(tmdbApiService.getPopularMovies()).thenReturn(fakeResponse);

        // when
        Map<String, Object> result = movieService.getPopularMovies();

        // then
        assertThat(result).isNotNull();
        assertThat(result.get("results")).isNotNull();
        assertThat(((List<Map<String, String>>) result.get("results")).get(0).get("title")).isEqualTo("Test Movie");
    }

    @Test
    @DisplayName("TMDB API에서 특정 영화의 세부 정보를 가져옴")
    void testGetMovieDetails() {
        // given
        String movieId = "12345";
        Map<String, Object> fakeMovieDetails = Map.of("title", "Test Movie", "id", movieId);
        when(tmdbApiService.getMovieDetails(anyString())).thenReturn(fakeMovieDetails);

        // when
        Map<String, Object> result = movieService.getMovieDetails(movieId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.get("title")).isEqualTo("Test Movie");
        assertThat(result.get("id")).isEqualTo(movieId);
    }


}

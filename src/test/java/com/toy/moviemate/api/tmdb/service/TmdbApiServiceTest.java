package com.toy.moviemate.api.tmdb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TmdbApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TmdbApiService tmdbApiService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("TMDB 서비스에서 영화 목록을 가져와야 함")
    void testGetPopularMovies() {
        // given
        Map<String, Object> fakeResponse = Map.of("results", List.of(Map.of("title", "Fake Movie")));
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(fakeResponse);

        // when
        Map<String, Object> result = tmdbApiService.getPopularMovies();

        // then
        assertThat(result).isNotNull();
        assertThat(result.get("results")).isNotNull();
        assertThat(((List<Map<String, String>>) result.get("results")).get(0).get("title")).isEqualTo("Fake Movie");
    }

    @Test
    @DisplayName("영화 세부 정보를 성공적으로 가져와야 함")
    void testGetMovieDetails_Success() {
        // given
        String movieId = "12345";
        Map<String, Object> fakeResponse = Map.of("title", "Fake Movie");
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(fakeResponse);

        // when
        Map<String, Object> result = tmdbApiService.getMovieDetails(movieId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.get("title")).isEqualTo("Fake Movie");
    }

    @Test
    @DisplayName("잘못된 영화 ID로 요청 시 예외가 발생해야 함")
    void testGetMovieDetails_InvalidId_ThrowsException() {
        // given
        String invalidMovieId = "999999";
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenThrow(new RuntimeException("영화 정보를 가져올 수 없습니다."));

        // when & then
        assertThrows(RuntimeException.class, () -> tmdbApiService.getMovieDetails(invalidMovieId));
    }
}

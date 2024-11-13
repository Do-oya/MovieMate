package com.toy.moviemate.domain.movie.tmdb;

import com.toy.moviemate.domain.movie.tmdb.impl.TmdbApiServiceImpl;
import org.assertj.core.api.Assertions;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TmdbApiServiceMockTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TmdbApiServiceImpl tmdbApiService;

    @Test
    @DisplayName("인기 영화 목록 조회 시 RestTemplate이 호출되고 올바른 응답이 반환된다")
    void getPopularMoviesTest() {
        // Given
        Map<String, Object> mockResponse = Map.of("results", List.of(Map.of("id", 1, "title", "Popular Movie")));
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // When
        Map<String, Object> response = tmdbApiService.getPopularMovies();

        // then
        assertThat(response).isNotNull();
        assertThat(response.get("results")).isInstanceOf(List.class);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }
}

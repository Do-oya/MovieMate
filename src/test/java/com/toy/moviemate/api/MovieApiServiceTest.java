package com.toy.moviemate.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MovieApiServiceTest {

    @Mock
    private RestTemplate restTemplate; // RestTemplate를 Mock으로 처리

    @InjectMocks
    private MovieApiService movieApiService; // Mock 객체를 주입받는 서비스 클래스

    private final String fakeApiKey = "fake-api-key"; // 테스트용 API 키
    private final String fakeApiUrl = "https://api.themoviedb.org/3/movie";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movieApiService = new MovieApiService(restTemplate);
        movieApiService.apiKey = fakeApiKey;
        movieApiService.tmdbApiUrl = fakeApiUrl;
    }

    @DisplayName("영화 목록을 성공적으로 가져옴")
    @Test
    void testGetPopularMovies_Success() {
        // given
        Map<String, Object> fakeResponse = new HashMap<>();
        fakeResponse.put("page", 1);
        fakeResponse.put("results", new HashMap<>());

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(fakeResponse);

        // when
        Map<String, Object> result = movieApiService.getPopularMovies();

        // then
        assertNotNull(result);
        assertEquals(1, result.get("page"));
        assertTrue(result.containsKey("results"));

        // verify
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @DisplayName("영화 세부 정보를 성공적으로 가져온다")
    @Test
    void testGetMovieDetails_Success() {
        // given
        String movieId = "12345";
        Map<String, Object> fakeResponse = new HashMap<>();
        fakeResponse.put("id", movieId);
        fakeResponse.put("title", "Fake Movie");

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(fakeResponse);

        // when
        Map<String, Object> result = movieApiService.getMovieDetails(movieId);

        // then
        assertNotNull(result);
        assertEquals(movieId, result.get("id"));
        assertEquals("Fake Movie", result.get("title"));

        // verify
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @Test
    @DisplayName("유효하지 않은 영화 ID로 세부 정보를 가져올 때 예외가 발생한다")
    void testGetMovieDetails_InvalidId_ThrowsException() {
        // given
        String invalidMovieId = "99999";
        when(restTemplate.getForObject(anyString(), eq(Map.class)))
                .thenThrow(new HttpClientErrorException(org.springframework.http.HttpStatus.NOT_FOUND));

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            movieApiService.getMovieDetails(invalidMovieId);
        });

        // 예외 메시지가 예상과 일치하는지 확인
        assertEquals("영화 정보를 가져올 수 없습니다.", exception.getMessage());

        // verify
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }
}
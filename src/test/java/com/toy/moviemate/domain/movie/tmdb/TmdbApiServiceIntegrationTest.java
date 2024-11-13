package com.toy.moviemate.domain.movie.tmdb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class TmdbApiServiceIntegrationTest {

    @Autowired
    private TmdbApiService tmdbApiService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String tmdbApiUrl;

    @Test
    @DisplayName("현재 상영 중인 영화 목록을 올바르게 조회한다")
    void getNowPlayingMoviesTest() {
        // Given
        String expectedUrl = tmdbApiUrl + "/movie/now_playing?api_key=" + apiKey + "&language=ko-KR";
        String mockResponse = "{\"results\": [{\"id\": 3, \"title\": \"Now Playing Movie\"}]}";
        mockServer.expect(requestTo(expectedUrl))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        // When
        Map<String, Object> response = tmdbApiService.getNowPlayingMovies();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.containsKey("results")).isTrue();
        mockServer.verify();
    }
}

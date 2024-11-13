package com.toy.moviemate.domain.movie.tmdb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TmdbApiAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("API를 통해 개봉 예정 영화 목록을 조회한다")
    void getUpcomingMoviesAcceptanceTest() {
        // When
        ResponseEntity<String> response = restTemplate.getForEntity("/api/tmdb/upcoming", String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().contains("<html"));
        assertThat(response.getBody()).isNotNull();
    }
}

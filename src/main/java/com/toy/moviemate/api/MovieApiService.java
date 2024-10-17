package com.toy.moviemate.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class MovieApiService {

    @Value("${tmdb.api.key}")
    String apiKey;

    @Value("${tmdb.api.url}")
    String tmdbApiUrl;

    private final RestTemplate restTemplate;

    public MovieApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 영화 가져오기
    public Map<String, Object> getPopularMovies() {
        String url = tmdbApiUrl + "/popular?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

    // 영화 세부정보 가져오기
    public Map<String, Object> getMovieDetails(String movieId) {
        String url = tmdbApiUrl + "/" + movieId + "?api_key=" + apiKey + "&language=ko-KR";
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (HttpClientErrorException e) {
            log.error("API 요청 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("영화 정보를 가져올 수 없습니다.");
        }
    }
}
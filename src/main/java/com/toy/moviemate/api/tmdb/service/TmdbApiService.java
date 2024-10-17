package com.toy.moviemate.api.tmdb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TmdbApiService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String tmdbApiUrl;

    private final RestTemplate restTemplate;

    public TmdbApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // TMDB API에서 인기 영화 목록 가져오기
    public Map<String, Object> getPopularMovies() {
        String url = tmdbApiUrl + "/movie/popular?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

    // TMDB API에서 특정 영화의 세부 정보 가져오기
    public Map<String, Object> getMovieDetails(String movieId) {
        String url = tmdbApiUrl + "/movie/" + movieId + "?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }
}
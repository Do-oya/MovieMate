package com.toy.moviemate.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class MovieApiService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/popular";

    public Map<String, Object> getPopularMovies() {
        RestTemplate restTemplate = new RestTemplate();
        // API 호출 URL에 API 키와 언어 설정 추가
        String url = TMDB_API_URL + "?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }
}
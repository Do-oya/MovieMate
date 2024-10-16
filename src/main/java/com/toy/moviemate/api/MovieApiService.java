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
    private String apiKey;

    private final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/";

    private final RestTemplate restTemplate = new RestTemplate();

    // 인기 영화 목록 가져오기
    public Map<String, Object> getPopularMovies() {
        // API 호출 URL에 API 키와 언어 설정 추가
        String url = TMDB_API_URL + "?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

    // 특정 영화의 세부 정보 가져오기
    public Map<String, Object> getMovieDetails(String movieId) {
        String url = TMDB_API_URL + movieId + "?api_key=" + apiKey + "&language=ko-KR";
        try {
            return restTemplate.getForObject(url, Map.class);  // API 호출
        } catch (HttpClientErrorException e) {
            // API 호출 중 오류가 발생하면 예외를 잡고 로그에 출력
            log.error("API 요청 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("영화 정보를 가져올 수 없습니다.");
        }
    }
}
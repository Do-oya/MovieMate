package com.toy.moviemate.domain.movie.tmdb.impl;

import com.toy.moviemate.domain.movie.tmdb.TmdbApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmdbApiServiceImpl implements TmdbApiService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String tmdbApiUrl;

    private final RestTemplate restTemplate;

    @Override
    public Map<String, Object> getPopularMovies() {
        String url = tmdbApiUrl + "/movie/popular?api_key=" + apiKey + "&language=ko";
        log.info(url);
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getLatestMovies() {
        String url = tmdbApiUrl + "/movie/now_playing?api_key=" + apiKey + "&language=ko";
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getNowPlayingMovies() {
        String url = tmdbApiUrl + "/movie/now_playing?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getUpcomingMovies() {
        String url = tmdbApiUrl + "/movie/upcoming?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getKoreanMovies() {
        String url = tmdbApiUrl + "/discover/movie?api_key=" + apiKey + "&language=ko-KR&region=KR";
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getForeignMovies() {
        String url = tmdbApiUrl + "/discover/movie?api_key=" + apiKey + "&language=ko-KR&region!=KR";
        return restTemplate.getForObject(url, Map.class);
    }

    @Override
    public Map<String, Object> getMovieDetails(String movieId) {
        String url = tmdbApiUrl + "/movie/" + movieId + "?api_key=" + apiKey + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }
}
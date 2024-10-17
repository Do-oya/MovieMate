package com.toy.moviemate.domain.movie.service;

import com.toy.moviemate.api.tmdb.service.TmdbApiService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieService {

    private final TmdbApiService tmdbApiService;

    public MovieService(TmdbApiService tmdbApiService) {
        this.tmdbApiService = tmdbApiService;
    }

    // 인기 영화 목록 가져오기 (비즈니스 로직 포함)
    public Map<String, Object> getPopularMovies() {
        return tmdbApiService.getPopularMovies();
    }

    // 특정 영화의 세부 정보 가져오기 (비즈니스 로직 포함)
    public Map<String, Object> getMovieDetails(String movieId) {
        return tmdbApiService.getMovieDetails(movieId);
    }
}
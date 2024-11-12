package com.toy.moviemate.domain.movie.service.impl;

import com.toy.moviemate.api.tmdb.service.TmdbApiService;
import com.toy.moviemate.domain.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final TmdbApiService tmdbApiService;

    @Override
    public Map<String, Object> getPopularMovies() {
        return tmdbApiService.getPopularMovies();
    }

    @Override
    public Map<String, Object> getMovieDetails(String movieId) {
        return tmdbApiService.getMovieDetails(movieId);
    }
}
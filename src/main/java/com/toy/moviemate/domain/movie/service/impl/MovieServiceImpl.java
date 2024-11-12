package com.toy.moviemate.domain.movie.service.impl;

import com.toy.moviemate.api.tmdb.service.impl.TmdbApiServiceImpl;
import com.toy.moviemate.domain.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final TmdbApiServiceImpl tmdbApiService;

    @Override
    public Map<String, Object> getPopularMovies() {
        return tmdbApiService.getPopularMovies();
    }

    @Override
    public Map<String, Object> getLatestMovies() {
        return tmdbApiService.getLatestMovies();
    }

    @Override
    public Map<String, Object> getNowPlayingMovies() {
        return tmdbApiService.getNowPlayingMovies();
    }

    @Override
    public Map<String, Object> getUpcomingMovies() {
        return tmdbApiService.getUpcomingMovies();
    }

    @Override
    public Map<String, Object> getKoreanMovies() {
        return tmdbApiService.getKoreanMovies();
    }

    @Override
    public Map<String, Object> getForeignMovies() {
        return tmdbApiService.getForeignMovies();
    }

    @Override
    public Map<String, Object> getMovieDetails(String movieId) {
        return tmdbApiService.getMovieDetails(movieId);
    }
}
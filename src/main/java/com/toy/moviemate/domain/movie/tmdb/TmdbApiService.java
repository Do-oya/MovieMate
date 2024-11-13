package com.toy.moviemate.domain.movie.tmdb;

import java.util.Map;

public interface TmdbApiService {
    Map<String, Object> getPopularMovies();
    Map<String, Object> getLatestMovies();
    Map<String, Object> getNowPlayingMovies();
    Map<String, Object> getUpcomingMovies();
    Map<String, Object> getKoreanMovies();
    Map<String, Object> getForeignMovies();
    Map<String, Object> getMovieDetails(String movieId);
}

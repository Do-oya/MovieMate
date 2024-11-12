package com.toy.moviemate.domain.movie.service;

import java.util.Map;

public interface MovieService {
    Map<String, Object> getPopularMovies();
    Map<String, Object> getMovieDetails(String movieId);
}

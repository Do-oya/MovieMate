package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.user.dto.UserDto;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewDto reviewDto);
    ReviewDto getReviewById(Long reviewId);
    void updateReview(ReviewDto reviewDto);
    void deleteReview(Long reviewId);
    List<ReviewDto> getReviewsByMovieId(String movieId);
    ReviewDto createReview(String movieId, String comment, Double rating);
    ReviewDto editReview(Long reviewId, String comment, Double rating);
}

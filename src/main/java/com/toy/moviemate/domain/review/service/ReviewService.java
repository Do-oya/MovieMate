package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(ReviewDto reviewDto) {
        Review review = Review.createReview(reviewDto.getComment(), reviewDto.getRating(), reviewDto.getMovieId());
        reviewRepository.save(review);
    }

    public List<ReviewDto> getReviewsByMovieId(String movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream()
                .map(review -> new ReviewDto(review.getId(), review.getComment(), review.getRating(), review.getMovieId()))
                .collect(Collectors.toList());
    }

    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        review.update(reviewDto.getComment(), reviewDto.getRating());
        reviewRepository.save(review);
    }

    public ReviewDto getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Review not found" + reviewId));
        return new ReviewDto(review.getId(), review.getComment(), review.getRating(), review.getMovieId());
    }
}
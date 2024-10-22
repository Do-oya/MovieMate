package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewSaveService {

    private final ReviewRepository reviewRepository;

    public ReviewSaveService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(ReviewDto reviewDto) {
        Review review = Review.createReview(reviewDto.getComment(), reviewDto.getRating(), reviewDto.getMovieId());
        reviewRepository.save(review);
    }
}

package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewUpdateService {

    private final ReviewRepository reviewRepository;

    public ReviewUpdateService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        review.update(reviewDto.getComment(), reviewDto.getRating());
        reviewRepository.save(review);
    }
}

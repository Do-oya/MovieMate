package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeleteService {

    private final ReviewRepository reviewRepository;

    public ReviewDeleteService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다. ID :" + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}

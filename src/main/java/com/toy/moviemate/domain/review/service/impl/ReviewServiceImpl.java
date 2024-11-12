package com.toy.moviemate.domain.review.service.impl;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.mapper.ReviewMapper;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import com.toy.moviemate.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        reviewRepository.save(review);
    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. ID:" + reviewId));
        return reviewMapper.toDto(review);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. ID:" + reviewDto.getId()));
        review.update(reviewDto.getComment(), reviewDto.getRating());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다. ID:" + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewDto> getReviewsByMovieId(String movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto createReview(String movieId, String comment, Double rating) {
        ReviewDto reviewDto = ReviewDto.builder()
                .movieId(movieId)
                .comment(comment)
                .rating(rating)
                .build();
        saveReview(reviewDto);
        return reviewDto;
    }

    @Override
    public ReviewDto editReview(Long reviewId, String comment, Double rating) {
        ReviewDto reviewDto = getReviewById(reviewId);
        reviewDto.setComment(comment);
        reviewDto.setRating(rating);
        updateReview(reviewDto);
        return reviewDto;
    }
}

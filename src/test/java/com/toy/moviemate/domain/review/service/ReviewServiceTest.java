package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("리뷰가 성공적으로 저장되어야 함")
    void testSaveReview() {
        // given
        ReviewDto reviewDto = ReviewDto.builder()
                .movieId("12345")
                .comment("Good Movie!")
                .rating(4.5)
                .build();

        Review review = Review.createReview(reviewDto.getComment(), reviewDto.getRating(), reviewDto.getMovieId());

        // when
        reviewService.saveReview(reviewDto);

        // then
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    @DisplayName("영화 ID로 리뷰 목록을 성공적으로 반환해야 함")
    void testGetReviewByMovieId() {
        // given
        String movieId = "12345";
        Review review = Review.createReview("Good Movie!", 4.5, movieId);
        review.setId(1L);

        when(reviewRepository.findByMovieId(movieId)).thenReturn(List.of(review));

        // when
        List<ReviewDto> reviews = reviewService.getReviewsByMovieId(movieId);

        // then
        assertEquals(1, reviews.size());
        assertEquals(review.getComment(), reviews.get(0).getComment());
        assertEquals(review.getRating(), reviews.get(0).getRating());
        assertEquals(review.getMovieId(), reviews.get(0).getMovieId());

        verify(reviewRepository, times(1)).findByMovieId(movieId);
    }

    @Test
    @DisplayName("리뷰 수정이 성공적으로 처리되어야 함")
    void testUpdateReview() {
        // given
        Long reviewId = 1L;
        Review existingReview = new Review();
        existingReview.setId(reviewId);
        existingReview.setComment("Good movie!");
        existingReview.setRating(4.5);
        existingReview.setMovieId("12345");

        ReviewDto updatedReviewDto = ReviewDto.builder()
                .id(reviewId)
                .comment("Updated Comment")
                .rating(4.8)
                .build();

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));

        // when
        reviewService.updateReview(updatedReviewDto);

        // then
        verify(reviewRepository).save(any(Review.class));
    }
}

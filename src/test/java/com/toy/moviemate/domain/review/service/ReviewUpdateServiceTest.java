package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReviewUpdateServiceTest {

    @InjectMocks
    private ReviewUpdateService reviewUpdateService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        reviewUpdateService.updateReview(updatedReviewDto);

        // then
        verify(reviewRepository).save(any(Review.class));
    }
}

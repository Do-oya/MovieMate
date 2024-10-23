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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReviewSaveServiceTest {

    @InjectMocks
    private ReviewSaveService reviewSaveService;

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

        // when
        reviewSaveService.saveReview(reviewDto);

        // then
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}

package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ReviewDeleteServiceTest {

    @InjectMocks
    private ReviewDeleteService reviewDeleteService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("리뷰 삭제 시 존재하지 않는 리뷰일 경우 예외 발생")
    void testDeleteReview_NotFound() {
        // given
        Long reviewId = 999L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> reviewDeleteService.deleteReview(reviewId));
        verify(reviewRepository, never()).deleteById(reviewId);
    }
}
package com.toy.moviemate.domain.review.service;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import com.toy.moviemate.domain.review.mapper.ReviewMapper;
import com.toy.moviemate.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewQueryServiceTest {

    @InjectMocks
    private ReviewQueryService reviewQueryService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("영화 ID로 리뷰 목록을 성공적으로 반환해야 함")
    void testGetReviewByMovieId() {
        // given
        Review review = Review.builder()
                .comment("Good Movie!")
                .rating(4.5)
                .movieId("12345")
                .build();

        ReviewDto reviewDto = ReviewDto.builder()
                .comment(review.getComment())
                .rating(review.getRating())
                .movieId(review.getMovieId())
                .build();

        when(reviewRepository.findByMovieId(review.getMovieId())).thenReturn(List.of(review));
        when(reviewMapper.toDto(review)).thenReturn(reviewDto);

        // when
        List<ReviewDto> reviews = reviewQueryService.getReviewsByMovieId(review.getMovieId());

        // then
        assertEquals(1, reviews.size());
        assertEquals(review.getComment(), reviews.get(0).getComment());
        assertEquals(review.getRating(), reviews.get(0).getRating());
        assertEquals(review.getMovieId(), reviews.get(0).getMovieId());

        verify(reviewRepository, times(1)).findByMovieId(review.getMovieId());
    }
}

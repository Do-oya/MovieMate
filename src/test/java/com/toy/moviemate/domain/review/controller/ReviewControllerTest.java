package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewDeleteService;
import com.toy.moviemate.domain.review.service.ReviewQueryService;
import com.toy.moviemate.domain.review.service.ReviewSaveService;
import com.toy.moviemate.domain.review.service.ReviewUpdateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewQueryService reviewQueryService;

    @MockBean
    private ReviewSaveService reviewSaveService;

    @MockBean
    private ReviewUpdateService reviewUpdateService;

    @MockBean
    private ReviewDeleteService reviewDeleteService;

    @Test
    @DisplayName("리뷰 작성 폼이 성공적으로 렌더링되어야 함")
    void testShowReviewForm() throws Exception {
        mockMvc.perform(get("/reviews/new").param("movieId", "12345"))
                .andExpect(status().isOk())
                .andExpect(view().name("review-form"))
                .andExpect(model().attributeExists("movieId"))
                .andExpect(model().attribute("movieId", "12345"));
    }

    @Test
    @DisplayName("리뷰 작성 요청이 성공적으로 처리되어야 함")
    void testSubmitReview() throws Exception {
        // given
        ReviewDto reviewDto = ReviewDto.builder()
                .movieId("12345")
                .comment("Good Movie!")
                .rating(4.5)
                .build();

        // when & then
        mockMvc.perform(post("/reviews")
                .param("movieId", "12345")
                .param("comment", "Good Movie!")
                .param("rating", "4.5"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/movies/12345"));

        // verify
        verify(reviewSaveService).saveReview(any(ReviewDto.class));
    }

    @Test
    @DisplayName("리뷰 수정 폼이 성공적으로 렌더링 되어야 함")
    void testRenderEditReviewForm() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .id(1L)
                .movieId("12345")
                .comment("Good movie!")
                .rating(4.5)
                .build();

        when(reviewQueryService.getReviewById(reviewDto.getId())).thenReturn(reviewDto);

        mockMvc.perform(get("/reviews/{reviewId}/edit", reviewDto.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("review-edit-form"))
                .andExpect(model().attributeExists("review"))
                .andExpect(model().attribute("review", reviewDto));
    }

    @Test
    @DisplayName("리뷰 수정 요청이 성공적으로 처리되어야 함")
    void testUpdateReview() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .id(1L)
                .movieId("12345")
                .comment("Updated Comment")
                .rating(4.8)
                .build();

        Mockito.doNothing().when(reviewUpdateService).updateReview(any(ReviewDto.class));

        mockMvc.perform(post("/reviews/update/{reviewId}", reviewDto.getId())
                        .param("movieId", reviewDto.getMovieId())
                        .param("comment", reviewDto.getComment())
                        .param("rating", String.valueOf(reviewDto.getRating()))
                        .param("_method", "put"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies/" + reviewDto.getMovieId()));

        verify(reviewUpdateService).updateReview(any(ReviewDto.class));
    }
}

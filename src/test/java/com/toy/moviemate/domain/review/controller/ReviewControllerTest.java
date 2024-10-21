package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

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
        verify(reviewService).saveReview(any(ReviewDto.class));
    }
}

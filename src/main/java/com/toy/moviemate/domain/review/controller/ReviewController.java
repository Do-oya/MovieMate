package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewDeleteService;
import com.toy.moviemate.domain.review.service.ReviewQueryService;
import com.toy.moviemate.domain.review.service.ReviewSaveService;
import com.toy.moviemate.domain.review.service.ReviewUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewSaveService reviewSaveService;
    private final ReviewUpdateService reviewUpdateService;
    private final ReviewDeleteService reviewDeleteService;

    public ReviewController(ReviewQueryService reviewQueryService, ReviewSaveService reviewSaveService, ReviewUpdateService reviewUpdateService, ReviewDeleteService reviewDeleteService) {
        this.reviewQueryService = reviewQueryService;
        this.reviewSaveService = reviewSaveService;
        this.reviewUpdateService = reviewUpdateService;
        this.reviewDeleteService = reviewDeleteService;
    }

    // 리뷰 작성 폼
    @GetMapping("/reviews/new")
    public String showReviewForm(@RequestParam("movieId") String movieId, Model model) {
        model.addAttribute("movieId", movieId);
        return "review/review-form";
    }

    // 리뷰 저장 요청 처리
    @PostMapping("/reviews")
    public String submitReview(@RequestParam("movieId") String movieId,
                               @RequestParam("comment") String comment,
                               @RequestParam("rating") Double rating) {
        ReviewDto reviewDto = ReviewDto.builder()
                .movieId(movieId)
                .comment(comment)
                .rating(rating)
                .build();
        reviewSaveService.saveReview(reviewDto);
        return "redirect:/movies/" + movieId;
    }

    // 리뷰 수정 폼
    @GetMapping("/reviews/{reviewId}/edit")
    public String showReviewEditForm(@PathVariable Long reviewId, Model model) {
        ReviewDto reviewDto = reviewQueryService.getReviewById(reviewId);
        model.addAttribute("review", reviewDto);
        return "review/review-edit-form";
    }

    // 리뷰 수정 요청 처리
    @PostMapping("/reviews/update/{reviewId}")
    public String updateReview(@PathVariable Long reviewId,
                               @RequestParam("movieId") String movieId,
                               @RequestParam("comment") String comment,
                               @RequestParam("rating") Double rating) {
        log.info("reviewId:{}", reviewId);
        log.info("comment:{}", comment);
        ReviewDto reviewDto = ReviewDto.builder()
                .id(reviewId)
                .comment(comment)
                .rating(rating)
                .build();
        reviewUpdateService.updateReview(reviewDto);
        return "redirect:/movies/" + movieId;
    }

    // 리뷰 삭제 요청 처리
    @PostMapping("/reviews/delete/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewDeleteService.deleteReview(reviewId);
        return "redirect:/movies";
    }
}
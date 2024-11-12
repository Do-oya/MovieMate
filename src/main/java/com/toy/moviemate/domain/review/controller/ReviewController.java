package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

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
        reviewService.createReview(movieId, comment, rating);
        return "redirect:/movies/" + movieId;
    }

    // 리뷰 수정 폼
    @GetMapping("/reviews/{reviewId}/edit")
    public String showReviewEditForm(@PathVariable("reviewId") Long reviewId, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        model.addAttribute("review", reviewDto);
        return "review/review-edit-form";
    }

    // 리뷰 수정 요청 처리
    @PostMapping("/reviews/update/{reviewId}")
    public String updateReview(@PathVariable("reviewId") Long reviewId,
                               @RequestParam("movieId") String movieId,
                               @RequestParam("comment") String comment,
                               @RequestParam("rating") Double rating) {
        reviewService.editReview(reviewId, comment, rating);
        return "redirect:/movies/" + movieId;
    }

    // 리뷰 삭제 요청 처리
    @PostMapping("/reviews/delete/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/movies";
    }
}
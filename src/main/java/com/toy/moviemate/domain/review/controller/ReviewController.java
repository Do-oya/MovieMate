package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/new")
    public String showReviewForm(@RequestParam("movieId") String movieId, Model model) {
        model.addAttribute("movieId", movieId);
        return "review-form";
    }

    @PostMapping("/reviews")
    public String submitReview(@RequestParam("movieId") String movieId,
                               @RequestParam("comment") String comment,
                               @RequestParam("rating") Double rating) {
        ReviewDto reviewDto = ReviewDto.builder()
                .movieId(movieId)
                .comment(comment)
                .rating(rating)
                .build();
        reviewService.saveReview(reviewDto);
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/reviews/{reviewId}/edit")
    public String showReviewEditForm(@PathVariable Long reviewId, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        model.addAttribute("review", reviewDto);
        return "review-edit-form";
    }

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
        reviewService.updateReview(reviewDto);
        return "redirect:/movies/" + movieId;
    }
}
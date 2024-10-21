package com.toy.moviemate.domain.review.controller;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        log.info(movieId);
        log.info(comment);
        log.info(String.valueOf(rating));
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovieId(movieId);
        reviewDto.setComment(comment);
        reviewDto.setRating(rating);
        reviewService.saveReview(reviewDto);
        return "redirect:/movies/" + movieId;
    }
}
package com.toy.moviemate.domain.review.dto;

import com.toy.moviemate.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String comment;
    private Double rating;
    private String movieId;

    public Review toEntity() {
        return Review.builder()
                .comment(this.comment)
                .rating(this.rating)
                .movieId(this.movieId)
                .build();
    }
}

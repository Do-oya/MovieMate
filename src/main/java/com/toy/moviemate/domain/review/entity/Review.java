package com.toy.moviemate.domain.review.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Double rating;

    private String movieId;

    public static Review createReview(String comment, Double rating, String movieId) {
        return Review.builder()
                .comment(comment)
                .rating(rating)
                .movieId(movieId)
                .build();
    }
}

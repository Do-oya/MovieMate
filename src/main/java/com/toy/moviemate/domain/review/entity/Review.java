package com.toy.moviemate.domain.review.entity;

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

    public void update(String comment, Double rating) {
        this.comment = comment;
        this.rating = rating;
    }

    /* 테스트 용 setter */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

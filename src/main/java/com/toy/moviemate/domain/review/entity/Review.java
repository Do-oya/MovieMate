package com.toy.moviemate.domain.review.entity;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public void update(String comment, Double rating) {
        this.comment = comment;
        this.rating = rating;
    }
}
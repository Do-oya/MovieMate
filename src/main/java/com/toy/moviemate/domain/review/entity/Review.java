package com.toy.moviemate.domain.review.entity;

import com.toy.moviemate.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

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
    private String username;

    public void update(String comment, Double rating) {
        this.comment = comment;
        this.rating = rating;
    }
}
package com.toy.moviemate.domain.review.repository;

import com.toy.moviemate.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(String movieId);
}

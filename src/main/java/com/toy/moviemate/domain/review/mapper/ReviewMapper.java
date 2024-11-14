package com.toy.moviemate.domain.review.mapper;

import com.toy.moviemate.domain.review.dto.ReviewDto;
import com.toy.moviemate.domain.review.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final ModelMapper modelMapper;

    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Review toEntity(ReviewDto reviewDto) {
        return Review.builder()
                .comment(reviewDto.getComment())
                .rating(reviewDto.getRating())
                .movieId(reviewDto.getMovieId())
                .username(reviewDto.getUsername())
                .build();
    }

    public ReviewDto toDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }
}
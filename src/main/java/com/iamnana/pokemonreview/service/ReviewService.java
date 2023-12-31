package com.iamnana.pokemonreview.service;

import com.iamnana.pokemonreview.dto.ReviewDTO;
import com.iamnana.pokemonreview.model.Review;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO);
    List<ReviewDTO> getReviewsByPokemonId(int id);
    ReviewDTO getReviewById(int reviewId, int pokemonId);

    ReviewDTO updateReview(int pokemonId, int reviewId, ReviewDTO reviewDTO);

    void deleteReview(int pokemonId, int reviewId);
}

package com.iamnana.pokemonreview.repository;

import com.iamnana.pokemonreview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int pokemonId);
}

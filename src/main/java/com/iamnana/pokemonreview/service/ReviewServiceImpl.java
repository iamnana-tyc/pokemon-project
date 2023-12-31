package com.iamnana.pokemonreview.service;

import com.iamnana.pokemonreview.dto.ReviewDTO;
import com.iamnana.pokemonreview.exceptions.PokemonNotFoundException;
import com.iamnana.pokemonreview.exceptions.ReviewNotFoundException;
import com.iamnana.pokemonreview.model.Pokemon;
import com.iamnana.pokemonreview.model.Review;
import com.iamnana.pokemonreview.repository.PokemonRepository;
import com.iamnana.pokemonreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO) {
        Review review = mapToEntity(reviewDTO);

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));

        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);

        return mapToDTO(newReview);
    }

    @Override
    public List<ReviewDTO> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review->mapToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        return mapToDTO(review);
    }

    @Override
    public ReviewDTO updateReview(int pokemonId, int reviewId, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDTO(updateReview);
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        reviewRepository.delete(review);
    }

    private ReviewDTO mapToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setStars(review.getStars());
        return reviewDTO;
    }
    private Review mapToEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());
        return review;
    }
}

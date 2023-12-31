package com.iamnana.pokemonreview.controller;

import com.iamnana.pokemonreview.dto.ReviewDTO;
import com.iamnana.pokemonreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "pokemonId") int pokemonId,
        @RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDTO), HttpStatus.CREATED);
    }
    @GetMapping("/pokemon/{pokemonId}/reviews")
    public List<ReviewDTO> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId){
        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable(value = "pokemonId") int pokemonId,
                                                   @PathVariable(value = "id") int id){
        ReviewDTO reviewDTO = reviewService.getReviewById(pokemonId,id);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable(value = "pokemonId") int pokemonId,
                                                  @PathVariable(value = "id") int id,
                                                  @RequestBody ReviewDTO reviewDTO){
        ReviewDTO updatedReview = reviewService.updateReview(pokemonId,id,reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @DeleteMapping("/pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") int pokemonId,
                                               @PathVariable(value = "id") int id){
        reviewService.deleteReview(pokemonId,id);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}

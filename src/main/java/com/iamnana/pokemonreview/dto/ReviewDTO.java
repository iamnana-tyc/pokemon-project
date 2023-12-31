package com.iamnana.pokemonreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private String title;
    private String content;
    private int stars;
}

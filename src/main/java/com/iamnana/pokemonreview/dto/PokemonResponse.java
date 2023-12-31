package com.iamnana.pokemonreview.dto;

import lombok.Data;

import java.util.List;
@Data
public class PokemonResponse {
    private List<PokemonDTO> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private Boolean last;

}

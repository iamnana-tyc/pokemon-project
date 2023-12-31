package com.iamnana.pokemonreview.service;

import com.iamnana.pokemonreview.dto.PokemonDTO;
import com.iamnana.pokemonreview.dto.PokemonResponse;
import com.iamnana.pokemonreview.model.Pokemon;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDTO getPokemonById(int id);

    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);

    void deletePokemon(int id);
}

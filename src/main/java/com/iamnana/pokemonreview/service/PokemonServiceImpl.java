package com.iamnana.pokemonreview.service;

import com.iamnana.pokemonreview.dto.PokemonDTO;
import com.iamnana.pokemonreview.dto.PokemonResponse;
import com.iamnana.pokemonreview.exceptions.PokemonNotFoundException;
import com.iamnana.pokemonreview.model.Pokemon;
import com.iamnana.pokemonreview.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDTO pokemonResponse = new PokemonDTO();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());

        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemon = pokemons.getContent();
        List<PokemonDTO> content =  listOfPokemon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDTO getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id) {
        Pokemon pokemon = pokemonRepository.
                findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon could not be updated!"));

        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());

        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).
                orElseThrow(()-> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
    }

    // This is to create our own Mapper -
    // maps properties from the source object to the target object based on their names and types.
   private PokemonDTO mapToDto(Pokemon pokemon){
        PokemonDTO pokemonDto = new PokemonDTO();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
   }

   private Pokemon mapToEntity(PokemonDTO pokemonDTO){
        Pokemon pk = new Pokemon();
        pk.setName(pokemonDTO.getName());
        pk.setType(pokemonDTO.getType());
        return pk;
   }
}

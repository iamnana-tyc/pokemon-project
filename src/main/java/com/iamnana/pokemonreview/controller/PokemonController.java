package com.iamnana.pokemonreview.controller;

import com.iamnana.pokemonreview.dto.PokemonDTO;
import com.iamnana.pokemonreview.dto.PokemonResponse;
import com.iamnana.pokemonreview.model.Pokemon;
import com.iamnana.pokemonreview.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
    {
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> pokemonDetail(@PathVariable int id){
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }


    @PostMapping("pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@RequestBody PokemonDTO pokemonDTO,
                                                    @PathVariable int id ){

        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonDTO,id),HttpStatus.OK);
    }
    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable int id){
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon deleted!", HttpStatus.OK);
    }
}

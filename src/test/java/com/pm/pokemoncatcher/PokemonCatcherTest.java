/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher;

import com.pm.pokemoncatcher.strategies.PokemonCatcherInterface;

/**
 *
 * @author joanapinto
 */
public abstract class PokemonCatcherTest {
    
    abstract void initialize();
    
       /**
     * Walk through pathInCardinalPoints and catch pokemons.
     *
     * @param pokemonCatcher
     * @param pathInCardinalPoints path to walk.
     */
    public void processPath(PokemonCatcherInterface pokemonCatcher, String pathInCardinalPoints) {
        for (int i = 0; i < pathInCardinalPoints.length(); i++) {
            char cardinalPoint = pathInCardinalPoints.charAt(i);
            pokemonCatcher.walk(cardinalPoint);
            pokemonCatcher.catchPokemon();
        }
    }
}

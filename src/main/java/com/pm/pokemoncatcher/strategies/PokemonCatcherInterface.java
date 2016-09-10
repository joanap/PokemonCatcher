package com.pm.pokemoncatcher.strategies;

/**
 * MapWalker interface provides the methods catchPokemon, getNumberCaughtPokemons
 * and walk
 * @author joanapinto
 */
public interface PokemonCatcherInterface {

    /**
     * Aim to increment the number of pokemon catched if the current
     * location has a pokemon.
     */
    public void catchPokemon();

    /**
     * Aims to return the number of caught pokemons.
     * @return number of pokemons catched.
     */
    public long getNumberCaughtPokemons();

    /**
     * Aims to update the current location based on a direction.
     * @param direction direction to move.
     */
    public void walk(char direction);
}

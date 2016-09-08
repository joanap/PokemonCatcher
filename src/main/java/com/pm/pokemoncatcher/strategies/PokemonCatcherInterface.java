package com.pm.pokemoncatcher.strategies;

/**
 *
 * @author joanapinto
 */
public interface PokemonCatcherInterface {
    public void catchPokemon();
    public long getNumberCaughtPokemons();
    public void walk(char direction);
}

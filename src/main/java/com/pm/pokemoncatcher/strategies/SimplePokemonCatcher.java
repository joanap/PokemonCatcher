package com.pm.pokemoncatcher.strategies;

import com.pm.pokemoncatcher.mapper.MapWalker;
import com.pm.pokemoncatcher.mapper.PokemonMapper;
import com.pm.pokemoncatcher.mapper.Coordinate;
import java.util.HashSet;

/**
 *
 * @author joanapinto
 */
public final class SimplePokemonCatcher implements PokemonCatcherInterface {
    private final MapWalker _map = new PokemonMapper();
    private final HashSet<Coordinate> _pathInCoordinates = new HashSet<>();

    /**
     * Creates a new SimplePokemonCatcher object that has the portion of the
     * path walked and the current coordinate. Adds the initial coordinate to
     * the path.
     */
    public SimplePokemonCatcher() {
        catchPokemon();
    }

    /**
     * Update the walked path with the current coordinate. If the
     * current coordinate is already in the walked path, it is not updated.
     */
    @Override
    public void catchPokemon() {        
        _pathInCoordinates.add(_map.getCurrentLocation());
    }
    
    /**
     * Count the number of caught pokemons
     *
     * @return number of caught pokemons.
     */
    @Override
    public long getNumberCaughtPokemons() {        
        return this._pathInCoordinates.size();
    }

    @Override
    public void walk(char direction) {
        _map.walk(direction);
    }
}

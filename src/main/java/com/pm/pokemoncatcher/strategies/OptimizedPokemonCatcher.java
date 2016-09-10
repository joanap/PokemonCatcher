package com.pm.pokemoncatcher.strategies;

import com.pm.pokemoncatcher.mapper.Coordinate;
import com.pm.pokemoncatcher.mapper.MapWalker;
import com.pm.pokemoncatcher.mapper.PokemonMapper;
import java.util.HashMap;
import java.util.HashSet;

/**
 * OptimizedPokemonCatcher implements an optimized solution to catch pokemons. 
 * Use SquarePerimeterCache to manage a set of coordinates, grouped in a square's
 * perimeter.
 * When the square's perimeter is filled with its coordinates, the memory
 * occupied by the coordinates is released.
 * @author joanapinto
 */
public final class OptimizedPokemonCatcher implements PokemonCatcherInterface {

    
    private final MapWalker _map = new PokemonMapper();
    private final HashMap<Long, SquarePerimeterCache> _cachedSquaresPerimeters = new HashMap<>();
    private long _numberCaughtPokemons = 0;

    /**
     * Create a new OptimizedPokemonCatcher. Add the initial coordinate to
     * the path walked.
     */
    public OptimizedPokemonCatcher() {
        catchPokemon();
    }

    /**
     * Return coordinate component with maximum absolute value in a Coordinate,
     * which corresponds to the half width of the square that coordinate belongs.
     * @param coordinate
     * @return the coordinate component with maximum absolute value.
     */
    public long getBelongingSquareHalfWidth(Coordinate coordinate) {
        return Math.max(Math.abs(coordinate.x), Math.abs(coordinate.y));
    }

    /**
     * Analyse if the current coordinate is new or if is already in cache.
     * Increment the number of pokemons catched if the coordinate is new.
     * To check if a coordinate is new it use the SquarePerimeterCache of the
     * square assigned to the coordinate to analyse if the coordinate is in
     * cache.
     */
    @Override
    public void catchPokemon() {
        final Coordinate currentLocation = _map.getCurrentLocation();
        final long coordinateRadiusToCenter = getBelongingSquareHalfWidth(currentLocation);
        SquarePerimeterCache radiusCache = getCachedRadius(coordinateRadiusToCenter);

        if (radiusCache == null) {
            radiusCache = new SquarePerimeterCache(coordinateRadiusToCenter);
            _cachedSquaresPerimeters.put(coordinateRadiusToCenter, radiusCache);
        }

        if (radiusCache.catchPokemon(currentLocation)) {
            _numberCaughtPokemons++;
        }
    }

    /**
     * Update the current location based on a direction.
     * @param direction the direction to move.
     */
    @Override
    public void walk(char direction) {
        _map.walk(direction);
    }

    /**
     * Return the number of pokemon catched.
     * @return number of pokemon catched.
     */
    @Override
    public long getNumberCaughtPokemons() {
        return _numberCaughtPokemons;
    }
    
    /**
     * Returns SquarePerimeterCache that stores the group of coordinates
     * which current coordinate belongs. The group corresponds to the perimeter
     * of a square with origin in initial position.
     * @param currentRadius half width of the square that coordinate belongs.
     * @return cache of coordinates in a square.
     */
    private SquarePerimeterCache getCachedRadius(long currentRadius) {
        return _cachedSquaresPerimeters.get(currentRadius);
    }

    /**
     * Class that implements a cache to store groups of coordinates.
     * @author joanapinto
     */
    private final class SquarePerimeterCache {

        public final long radius;
        private long _numberRemainingPokemons;
        private HashSet<Coordinate> _caughtPokemonCoordinates = new HashSet<>();

        public SquarePerimeterCache(long radius) {
            this.radius = radius;
            _numberRemainingPokemons = getNumberOfCoordinatesInSquarePerimeter();
        }

        public long getNumberOfCoordinatesInSquarePerimeter() {
            return radius == 0 ? 1 : 8 * radius;
        }

        public boolean catchPokemon(Coordinate coordinate) {
            if (!didCaughtAllPokemons() && !alreadyCaughtPokemon(coordinate)) {
                if (onlyOnePokemonRemains()) {
                    _caughtPokemonCoordinates.clear();
                    _caughtPokemonCoordinates = null; //mark for GC
                } else {
                    _caughtPokemonCoordinates.add(coordinate);
                }

                this._numberRemainingPokemons = Math.max(0, this._numberRemainingPokemons - 1);

                return true;
            }

            return false;
        }

        public boolean didCaughtAllPokemons() {
            return this._numberRemainingPokemons == 0;
        }

        public boolean alreadyCaughtPokemon(Coordinate coordinate) {
            return this._caughtPokemonCoordinates.contains(coordinate);
        }

        public boolean onlyOnePokemonRemains() {
            return this._numberRemainingPokemons == 1;
        }
    }
}

package com.pm.pokemoncatcher.strategies;

import com.pm.pokemoncatcher.mapper.Coordinate;
import com.pm.pokemoncatcher.mapper.MapWalker;
import com.pm.pokemoncatcher.mapper.PokemonMapper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author joanapinto
 */
public final class OptimizedPokemonCatcher implements PokemonCatcherInterface {
    private final MapWalker _map = new PokemonMapper();
    private final HashMap<Long, SquarePerimeterCache> _cachedSquaresPerimeters = new HashMap<>();
    private long _numberCaughtPokemons = 0;

    /**
     * Creates a new OptimizedPokemonCatcher object that has the portion of the
     * path walked and the current coordinate. Adds the initial coordinate to
     * the path.
     */
    public OptimizedPokemonCatcher() {
        catchPokemon();
    }

    public long getMaximumDistanceToOrigin(Coordinate coordinate) {
        return Math.max(Math.abs(coordinate.x), Math.abs(coordinate.y));
    }

    @Override
    public void catchPokemon() {       
        final Coordinate currentLocation = _map.getCurrentLocation();
        final long coordinateRadiusToCenter = getMaximumDistanceToOrigin(currentLocation);
        SquarePerimeterCache radiusCache = getCachedRadius(coordinateRadiusToCenter);       
        
        if(radiusCache == null){
            radiusCache = new SquarePerimeterCache(coordinateRadiusToCenter);
            _cachedSquaresPerimeters.put(coordinateRadiusToCenter, radiusCache);
        }
        
        if(radiusCache.catchPokemon(currentLocation)){
            _numberCaughtPokemons++;
        }
    }
    
    @Override
    public void walk(char direction) {
        _map.walk(direction);
    }

    @Override
    public long getNumberCaughtPokemons() {
        return _numberCaughtPokemons;
    }

    private SquarePerimeterCache getCachedRadius(long currentRadius) {
        return _cachedSquaresPerimeters.get(currentRadius);        
    }
    
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
        
        public boolean catchPokemon(Coordinate coordinate){
            if(!didCaughtAllPokemons() && !alreadyCaughtPokemon(coordinate)){
                if(onlyOnePokemonRemains()){
                    _caughtPokemonCoordinates.clear();
                    _caughtPokemonCoordinates = null; //mark for GC
                }else {
                    _caughtPokemonCoordinates.add(coordinate);                    
                }
                
                this._numberRemainingPokemons = Math.max(0, this._numberRemainingPokemons - 1);

                return true;
            }
            
            return false;
        }
        
        public boolean didCaughtAllPokemons(){
            return this._numberRemainingPokemons == 0;
        }
        
        public boolean alreadyCaughtPokemon(Coordinate coordinate){
            return this._caughtPokemonCoordinates.contains(coordinate);
        }
        
        public boolean onlyOnePokemonRemains(){
            return this._numberRemainingPokemons == 1;
        }
    }
}
 
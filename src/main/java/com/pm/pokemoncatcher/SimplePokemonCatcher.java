package com.pm.pokemoncatcher;

import java.util.HashSet;

/**
 *
 * @author joanapinto
 */
public class SimplePokemonCatcher implements PokemonCatcherInterface {

    /**
     *
     */
    public static final char NORTH = 'N';
    public static final char SOUTH = 'S';
    public static final char EAST = 'E';
    public static final char WEST = 'O';

    public static final Coordinate INITIAL_COORDINATE = new Coordinate(0, 0);
    HashSet<Coordinate> pathInCoordinates = new HashSet<Coordinate>();
    Coordinate currentCoordinate = INITIAL_COORDINATE;

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
        pathInCoordinates.add(currentCoordinate);
    }

    /**
     * Convert the cardinal point direction in a coordinate. Update current
     * coordinate.
     *
     * @param direction the cardinal point to walk
     */
    @Override
    public void walk(char direction) {
        switch (direction) {
            case NORTH:
                currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1);
                break;
            case SOUTH:
                currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - 1);
                break;
            case EAST:
                currentCoordinate = new Coordinate(currentCoordinate.getX() + 1, currentCoordinate.getY());
                break;
            case WEST:
                currentCoordinate = new Coordinate(currentCoordinate.getX() - 1, currentCoordinate.getY());
                break;
        }
    }

    /**
     * Count the number of caught pokemons
     *
     * @return number of caught pokemons.
     */
    @Override
    public long getNumberCaughtPokemons() {
        return this.pathInCoordinates.size();
    }
}

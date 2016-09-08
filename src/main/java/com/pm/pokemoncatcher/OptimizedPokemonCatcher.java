package com.pm.pokemoncatcher;

import static java.lang.Long.max;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author joanapinto
 */
public class OptimizedPokemonCatcher implements PokemonCatcherInterface {

    /**
     *
     */
    public static final Coordinate INITIAL_COORDINATE = new Coordinate(0, 0);
    Coordinate currentCoordinate = INITIAL_COORDINATE;
    HashMap<Long, Long> coordinatesNumberInSquare = new HashMap<Long, Long>();
    HashMap<Long, HashSet<Coordinate>> coordinatesInSquare = new HashMap<Long, HashSet<Coordinate>>();
    long numberCaughtPokemons = 0;

    /**
     * Creates a new OptimizedPokemonCatcher object that has the portion of the
     * path walked and the current coordinate. Adds the initial coordinate to
     * the path.
     */
    public OptimizedPokemonCatcher() {
        catchPokemon();
    }

    public long numberOfCardinalPointsInSquare(long radius) {
        if(radius == 0) {
            return 1;
        }
        else {
            return 8 * radius;   
        }
    }

    public long squareOfCoordinate() {
        return max(abs(currentCoordinate.getX()), abs(currentCoordinate.getY()));
    }

    public boolean isNewSquare() {
        return !coordinatesNumberInSquare.containsKey(squareOfCoordinate());
    }

    public boolean isNewPokemon() {
        long currentSquare = squareOfCoordinate();
        if (coordinatesInSquare.containsKey(currentSquare)) {
            return !coordinatesInSquare.get(currentSquare).contains(currentCoordinate);
        } else {
            HashSet<Coordinate> newSquare = new HashSet<Coordinate>();
            newSquare.add(currentCoordinate);
            coordinatesInSquare.put(currentSquare, newSquare);
        }
        return true;
    }

    public void addPokemonToSquare() {
        long currentSquare = squareOfCoordinate();
        coordinatesNumberInSquare.put(currentSquare, coordinatesNumberInSquare.get(currentSquare) - 1);
        coordinatesInSquare.get(currentSquare).add(currentCoordinate);
    }

    public void removeFullSquare() {
        coordinatesInSquare.remove(squareOfCoordinate());
    }

    public boolean isFullSquare(long radius) {
        long currentSquare = squareOfCoordinate();
        if (isNewSquare()) {
            coordinatesNumberInSquare.put(currentSquare, numberOfCardinalPointsInSquare(currentSquare));
            return false;
        } else {
            return coordinatesNumberInSquare.get(currentSquare) == 0;
        }
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
            case 'N':
                currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1);
                break;
            case 'S':
                currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - 1);
                break;
            case 'E':
                currentCoordinate = new Coordinate(currentCoordinate.getX() + 1, currentCoordinate.getY());
                break;
            case 'O':
                currentCoordinate = new Coordinate(currentCoordinate.getX() - 1, currentCoordinate.getY());
                break;
        }
    }

    @Override
    public void catchPokemon() {
        if (isFullSquare(squareOfCoordinate())) {
            removeFullSquare();
        }
        else if (isNewPokemon()) {
            addPokemonToSquare();
            numberCaughtPokemons += 1;
        }
    }

    @Override
    public long getNumberCaughtPokemons() {
        return numberCaughtPokemons;
    }
}
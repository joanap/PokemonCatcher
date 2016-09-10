package com.pm.pokemoncatcher.mapper;

/**
 * PokemonMapper manage the current coordinate 
 * @author joanapinto
 */
public class PokemonMapper implements MapWalker {    

    public static final char NORTH = 'N';
    public static final char SOUTH = 'S';
    public static final char EAST = 'E';
    public static final char WEST = 'O';
    
    /**
     * Initial coordinate in the path.
     */
    public static final Coordinate INITIAL_COORDINATE = new Coordinate(0, 0);
    private Coordinate currentCoordinate = INITIAL_COORDINATE;

    /**
     * Convert the cardinal point direction in a coordinate. Update current
     * coordinate.
     *
     * @param direction the cardinal point to walk
     */
    @Override
    public void walk(char direction) {        
        switch (direction) {
            case PokemonMapper.NORTH:
                currentCoordinate = new Coordinate(currentCoordinate.x, currentCoordinate.y + 1);
                break;
            case PokemonMapper.SOUTH:
                currentCoordinate = new Coordinate(currentCoordinate.x, currentCoordinate.y - 1);
                break;
            case PokemonMapper.EAST:
                currentCoordinate = new Coordinate(currentCoordinate.x + 1, currentCoordinate.y);
                break;
            case PokemonMapper.WEST:
                currentCoordinate = new Coordinate(currentCoordinate.x - 1, currentCoordinate.y);
                break;
        }
    }

    @Override
    public Coordinate getCurrentLocation() {
        return currentCoordinate;
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher;

import java.util.HashSet;

/**
 *
 * @author joanapinto
 */
public class PokemonCatcher {

    /**
     * The initial x coordinate
     */
    public static final long FIRSTX = 0;

    /**
     * The initial y coordinate
     */
    public static final long FIRSTY = 0;
    
    HashSet<Coordinate> pathInCoordinates;
    
    Coordinate currentCoordinate;
    
    /**
     * Creates a new PokemonCatcher object that has the portion of the path
     * walked and the current coordinate.
     * Adds the initial coordinate to the path.
     */
    public PokemonCatcher() {
        this.pathInCoordinates =  new HashSet<Coordinate>();
        this.currentCoordinate = new Coordinate(FIRSTX,FIRSTY);
        catchFirstPokemon();
    }
    
    private void catchFirstPokemon() {
        pathInCoordinates.add(new Coordinate(FIRSTX,FIRSTY));
    }

    /**
     * Converts the @param cardinalPoint in a coordinate and updates the 
     * current coordinate; Updates the path already walked with the new
     * coordinate. If @param cardinalPoint does not be valid, it does nothing.
     * 
     * @param cardPoint the cardinal point to move to.
     */
    public void catchPokemon(String cardPoint) {        
        long newCoordinate = 0;
        if(cardPoint != null) {                
            switch (cardPoint.charAt(0)) {
                case 'N' :
                    newCoordinate = currentCoordinate.getY() + 1;
                    currentCoordinate.setY(newCoordinate);
                    pathInCoordinates.add(new Coordinate(currentCoordinate.getX(),
                    currentCoordinate.getY()));
                    break;
                case 'S': 
                    newCoordinate = currentCoordinate.getY() - 1;
                    currentCoordinate.setY(newCoordinate);
                    pathInCoordinates.add(new Coordinate(currentCoordinate.getX(),
                    currentCoordinate.getY()));
                    break;
                case 'E':
                    newCoordinate = currentCoordinate.getX() + 1;
                    currentCoordinate.setX(newCoordinate);
                    pathInCoordinates.add(new Coordinate(currentCoordinate.getX(),
                    currentCoordinate.getY()));
                    break;
                case 'O':
                    newCoordinate = currentCoordinate.getX() - 1;
                    currentCoordinate.setX(newCoordinate);
                    pathInCoordinates.add(new Coordinate(currentCoordinate.getX(),
                    currentCoordinate.getY()));
                    break;
            }
        }
    }
    
    /**
     * Process the path and makes use of catchPokemon method to catch a pokemon
     * in specific coordinate.
     * @param pathInCardinalPoints path to walk in cardinal points.
     */
    public void walk(String pathInCardinalPoints) {    
        String[] step = pathInCardinalPoints.split("");
        for(String cardPoint : step) {
            catchPokemon(cardPoint);
        }
    }

    /**
     * Returns the number of caught pokemons, which is the number of coordinates
     * in the path pathInCoordinates.
     * @return number of caught pokemons.
     */
    public long getCaughtPokemons() {
        return this.pathInCoordinates.size();
    }
}

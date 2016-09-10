/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher.mapper;

/**
 * MapWalker interface provides the methods walk and getCurrentLocation.
 * @author joanapinto
 */
public interface MapWalker {

    /**
     * Aims to update the current location based on a direction.
     * @param direction direction to move.
     */
    public void walk(char direction);

    /**
     * Aims to get the current Coordinate.
     * @return the current coordinate.
     */
    public Coordinate getCurrentLocation();
}

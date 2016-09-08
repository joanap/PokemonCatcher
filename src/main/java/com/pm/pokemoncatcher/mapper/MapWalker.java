/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher.mapper;

/**
 *
 * @author joanapinto
 */
public interface MapWalker {
    public void walk(char direction);
    public Coordinate getCurrentLocation();
}

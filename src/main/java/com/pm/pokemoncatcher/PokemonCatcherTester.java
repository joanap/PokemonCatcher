/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher;

import java.util.Scanner;

/**
 * The PokemonCatcherTester program implements an application that tests the
 * PokemonCatcher class with a path from standard input given by the user.
 * It prints the number of caught pokemons to standard output.
 * 
 * @author joanapinto
 */
public class PokemonCatcherTester {
       
    /**
     * Process the input in the scanner and returns a String with this
     * input; Returns null if there is no input.
     * @param in scanner with a path
     * @return
     */
    public String processInput(Scanner in) {       
        if(in != null) {
            if(in.hasNext()) {
                return in.next();
            }
        }
        return null;
    }
    
    /**
     * This is the main method which takes a path from standard input and prints
     * the number of caught pokemons in the path. 
     * @param args unused.
     */
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        PokemonCatcherTester test = new PokemonCatcherTester();
        String pathInCardinalPoints = test.processInput(scanner);
        PokemonCatcher pokemonCatcher = null;
        long caughtPokemons = 0;
        
        if(pathInCardinalPoints != null) {
            pokemonCatcher = new PokemonCatcher();
            pokemonCatcher.walk(pathInCardinalPoints);
        }
        if(pokemonCatcher != null) {
            caughtPokemons = pokemonCatcher.getCaughtPokemons();
        }        
        
        System.out.println(caughtPokemons);
    }
}

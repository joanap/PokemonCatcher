/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
 
import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * JUint test to the PokemonCatcher class
 * @author joanapinto
 */
@RunWith(JUnitParamsRunner.class)
public class PokemonCatcherTest {
    
   private PokemonCatcher pokemonCatcher;

    /**
     * Initialize the class PokemonCatcher to test
     */
    @Before
   public void initialize() {
      pokemonCatcher = new PokemonCatcher();
   }

    /**
     * Test if the number of caught pokemons is the expected number
     * The method is run with input paths in file src/test/resources/test.csv.
     * Print memory usage to standard output.
     * @param inputPath path to catch pokemons
     * @param expectedResult expected number of caught pokemons
     */
    @Test
    @FileParameters("src/test/resources/test.csv")
    public void testCaughtPokemons(String inputPath, long expectedResult) {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        pokemonCatcher.walk(inputPath);

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory: " + (usedMemoryAfter-usedMemoryBefore) + " bytes");

        assertEquals(expectedResult, pokemonCatcher.getCaughtPokemons());
    }
}

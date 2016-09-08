package com.pm.pokemoncatcher;

import com.pm.pokemoncatcher.strategies.OptimizedPokemonCatcher;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * JUint test to the SimplePokemonCatcher class
 *
 * @author joanapinto
 */
@RunWith(JUnitParamsRunner.class)
public class OptimizedPokemonCatcherTest {

    private OptimizedPokemonCatcher pokemonCatcher;

    /**
     * Initialize the class SimplePokemonCatcher to test
     */
    @Before
    public void initialize() {
        pokemonCatcher = new OptimizedPokemonCatcher();
    }

    /**
     * Walk through pathInCardinalPoints and catch pokemons.
     *
     * @param pathInCardinalPoints path to walk.
     */
    public void processPath(String pathInCardinalPoints) {
        for (int i = 0; i < pathInCardinalPoints.length(); i++) {
            char cardinalPoint = pathInCardinalPoints.charAt(i);
            pokemonCatcher.walk(cardinalPoint);
            pokemonCatcher.catchPokemon();
        }
    }

    /**
     * Test if the number of caught pokemons is the expected number The method
     * is run with input paths in file src/test/resources/test.csv. Print memory
     * usage to standard output.
     *
     * @param inputPath path to catch pokemons
     * @param expectedResult expected number of caught pokemons
     */
    @Test
    @FileParameters("src/test/resources/test.csv")
    public void testCaughtPokemons(String inputPath, long expectedResult) {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

        processPath(inputPath);
        
        System.gc(); //call GC
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory: " + (usedMemoryAfter - usedMemoryBefore) + " bytes");

        assertEquals(expectedResult, pokemonCatcher.getNumberCaughtPokemons());
    }
}

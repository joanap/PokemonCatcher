package com.pm.pokemoncatcher;

import com.pm.pokemoncatcher.strategies.OptimizedPokemonCatcher;
import com.pm.pokemoncatcher.strategies.PokemonCatcherInterface;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * JUint test to the OptimizedPokemonCatcher class
 *
 * @author joanapinto
 */
@RunWith(JUnitParamsRunner.class)
public class OptimizedPokemonCatcherTest extends PokemonCatcherTest{
    private PokemonCatcherInterface pokemonCatcher;

    /**
     * Initialize the class OptimizedPokemonCatcher to test
     */
    @Before
    @Override
    public void initialize() {
        pokemonCatcher = new OptimizedPokemonCatcher();
    }
    
     /**
     * Test if the number of caught pokemons is the expected number The method
     * is run with input paths in file src/test/resources/test.csv.
     * @param inputPath path to catch pokemons
     * @param expectedResult expected number of caught pokemons
     */
    @Test
    @FileParameters("src/test/resources/test.csv")
    public void testCaughtPokemons(String inputPath, long expectedResult) {
        processPath(pokemonCatcher, inputPath);
        assertEquals(expectedResult, pokemonCatcher.getNumberCaughtPokemons());
    }
}

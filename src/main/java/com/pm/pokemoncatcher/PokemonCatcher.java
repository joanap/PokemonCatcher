package com.pm.pokemoncatcher;

import com.pm.pokemoncatcher.strategies.OptimizedPokemonCatcher;
import com.pm.pokemoncatcher.strategies.PokemonCatcherInterface;
import java.util.Scanner;

/**
 * The PokemonCatcher program implements an application that tests the
 Optimized PokemonCatcherwith a path from standard input given in the sandard
 * input.
 * It prints the number of caught pokemons to standard output.
 * 
 * @author joanapinto
 */
public class PokemonCatcher {       
    private final PokemonCatcherInterface _pokemonCatcher = new OptimizedPokemonCatcher();
    
    /**
     * Returns the number of caught pokemons.
     * @return number of caught pokemons.
     */
    public long getNumberCaughtPokemons(){
        return _pokemonCatcher.getNumberCaughtPokemons();
    }
    
    /**
     * 
     * @param direction
     */
    public void walkAndCatchPokemon(char direction){
        _pokemonCatcher.walk(direction);
        _pokemonCatcher.catchPokemon();
    }
    
    /**
     * Process the input in the scanner and returns a String with this
     * input; Returns null if there is no input.
     * @param in scanner with a path
     * @return
     */
    public String processInput(Scanner in) {       
        if(in != null && in.hasNext()) {
            return in.next();
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
        PokemonCatcher catcher = new PokemonCatcher();
       
        String pathInCardinalPoints = catcher.processInput(scanner);

        for (int i = 0; i < pathInCardinalPoints.length(); i++){
            char direction = pathInCardinalPoints.charAt(i);    
            catcher.walkAndCatchPokemon(direction);            
        }
        System.out.println(catcher.getNumberCaughtPokemons());
    }
}

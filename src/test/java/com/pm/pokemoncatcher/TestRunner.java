package com.pm.pokemoncatcher;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * The TestRunner program implements an application that run JUnit tests
 *
 * @author joanapinto
 */
public class TestRunner {

    /**
     * This is the main method which makes use runClasses method to run
 SimplePokemonCatcherTest. Print the result of the tests to standard ouput
     *
     * @param args unused.
     */
    public static void main(String[] args) {        
        Result result = JUnitCore.runClasses(SimplePokemonCatcherTest.class, OptimizedPokemonCatcherTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}

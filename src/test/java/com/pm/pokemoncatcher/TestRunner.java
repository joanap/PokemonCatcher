/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pm.pokemoncatcher;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * The TestRunner program implements an application that run JUnit tests
 * @author joanapinto
 */
public class TestRunner {

    /**
     * This is the main method which makes use runClasses method to run
     * PokemonCatcherTest. Print the result of the tests to standard ouput
     * @param args unused.
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PokemonCatcherTest.class);
    
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
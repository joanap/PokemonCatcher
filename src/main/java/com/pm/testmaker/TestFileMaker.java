package com.pm.testmaker;

import com.pm.pokemoncatcher.strategies.SimplePokemonCatcher;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * TestFileMaker implements an application to write in a file several specific 
 * tests
 * @author joanapinto
 */
public class TestFileMaker {

    public static final char NORTH = 'N';
    public static final char SOUTH = 'S';
    public static final char EAST = 'E';
    public static final char WEST = 'O';
    
    private final HashMap<Integer, Character> intDirections = new HashMap<>();
    
    public TestFileMaker() {
        intDirections.put(0, NORTH);
        intDirections.put(1, SOUTH);
        intDirections.put(2, EAST);
        intDirections.put(3, WEST);
    }
    
  
    /**
     * Return an int between a range.
     * @param min minimum value in the range.
     * @param max maximum value in the range.
     * @return generated random value.
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     * Generate a random cardinal point. Makes use of randInt method to select a
     * random cardinal point.
     * @param min minimum value in the range.
     * @param max maximum value in the range.
     * @return cardinal point.
     */
    public char randomCardPoint(int min, int max) {
        int random = randInt(min,max);
        if(intDirections.containsKey(random)) {
            return intDirections.get(random);
        }
        return NORTH;
    }
    
    /**
     * Generate a line path with n (pathSize) cardinal points.
     * @param pathSize the number of cardinal points in the path.
     * @return path to walk.
     */
    public String line(long pathSize) {
        StringBuilder path = new StringBuilder();
        char cardPoint = randomCardPoint(0,intDirections.size() - 1);
        for(long i = 0; i < pathSize; i++) {
            path.append(cardPoint);
        }
        return path.toString();        
    }
    
    /**
     * Generate a random walk with n (pathSize) cardinal points.
     * @param pathSize the number of cardinal points in the path.
     * @return path to walk.
     */
    public String randomWalk(long pathSize) {
        StringBuilder path = new StringBuilder();
        for(long i = 0; i < pathSize; i++) {
            path.append(randomCardPoint(0,intDirections.size() - 1));
        }
        return path.toString();
    }

    /**
     * Add an invalid cardinal to a valid line path with n (pathSize)
     * cardinal points. 
     * @param pathSize the number of cardinal points in the valid line path.
     * @return path to walk.
     */
    public String wrongInput(long pathSize) {
        StringBuilder path = new StringBuilder();
        path.append('A');
        path.append(line(pathSize));
        return path.toString();
    }
    
    public String sShapeCycles(long radius) {
        StringBuilder path = new StringBuilder();
        for(int j = 0; j < radius-1; j++) {
            path.append(NORTH);
        }
        for(int j = 0; j < radius - 1; j++) {
            path.append(WEST);
        }
        for(int j = 0; j < radius*2 - 2; j++) {
            path.append(EAST);
        }
        for(int i = 0; i < radius - 1; i++) {
            path.append(SOUTH);
            for(int j = 0; j < radius*2 - 2; j++) {
                path.append(WEST);
            }
            path.append(SOUTH);
            for(int j = 0; j < radius*2 - 2; j++) {
                path.append(EAST);
            }
        }
        return path.toString();
    }
   
    /**
     * Return the number of caught pokemons in the path.
     * @param pathInCardinalPoints path to walk
     * @return number of caught pokemons.
     */
    public long processTest(String pathInCardinalPoints) {
        SimplePokemonCatcher pokemonCatcher = new SimplePokemonCatcher();
        for (int i = 0; i < pathInCardinalPoints.length(); i++){
            char cardinalPoint = pathInCardinalPoints.charAt(i);    
            pokemonCatcher.walk(cardinalPoint);
            pokemonCatcher.catchPokemon();
        }
        return pokemonCatcher.getNumberCaughtPokemons();
    }
    
    /**
     * Process the tests and prints result.
     * @param tests the tests to process.
     * @param out output channel.
     * @throws IOException
     */
    public void printTestsToFile(ArrayList<String> tests, PrintWriter out) throws IOException {       
        for(String path : tests) {
            long expectedCaughtPokemons = processTest(path);
            out.println(path + "," + expectedCaughtPokemons);
        }
    }
    
    /**
     * This is the main method that generates several tests and makes use of
     * printTestsToFile method to print results to "src/test/resources/test.csv"
     * file.
     * @param args unused.
     */
    public static void main(String[] args) {
        TestFileMaker testFileMaker = new TestFileMaker();
        ArrayList<String> testPaths =  new ArrayList<>();
           
        testPaths.add("E");
        testPaths.add("NESO");
        testPaths.add("NSNSNSNSNS");
        testPaths.add(testFileMaker.line(10000));
        testPaths.add(testFileMaker.wrongInput(2000));
        testPaths.add(testFileMaker.sShapeCycles(500));
        testPaths.add(testFileMaker.randomWalk(1000000));

        try {            
            File file = new File("src/test/resources/test.csv");
            if(!file.exists()){
                file.createNewFile();
            }           
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);            
            testFileMaker.printTestsToFile(testPaths, out);
            bw.close();
            out.close();            
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


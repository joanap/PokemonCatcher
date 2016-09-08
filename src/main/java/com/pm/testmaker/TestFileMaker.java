package com.pm.testmaker;

import com.pm.pokemoncatcher.strategies.SimplePokemonCatcher;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    /**
     * The cardinal points of any path
     */
    public static final String[] cardinalPoints = {"N","E","S","O"};
    
    /**
     *
     * @param originalArray
     * @return
     */
    public String[] invertArray(String[] originalArray) {
        String[] invertedArray = originalArray;
        for(int i = 0; i < invertedArray.length / 2; i++) {
            String temp = invertedArray[i];
            invertedArray[i] = invertedArray[invertedArray.length - i - 1];
            invertedArray[invertedArray.length - i - 1] = temp;
        }
        return invertedArray;
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
    public String randomCardPoint(int min, int max) {
        int random = randInt(0,cardinalPoints.length-1);
        switch(random) {
            case 0:
                return "N";
            case 1:
                return "E";
            case 2:
                return "S";
            case 3:
                return "O";
            default:
                return "N";
        }
    }
    
    /**
     * Generate a quadrangular cycle path with an edge of n (diameter) cardinal
     * points.
     * @param diameter the number of cardinal points in an edge.
     * @param cardPoints the available cardinal points.
     * @return path to walk.
     */
    public String cycle(long diameter, String[] cardPoints) {
        String path = "";
        for(String cardPoint: cardPoints) {
            for(long i = 0; i < diameter-1; i++) {
                path += cardPoint;
            }
        }
        return path;
    }
    
    /**
     * Generate a line path with n (pathSize) cardinal points.
     * @param pathSize the number of cardinal points in the path.
     * @return path to walk.
     */
    public String line(long pathSize) {
        String path = "";
        String cardPoint = randomCardPoint(0,cardinalPoints.length-1);
        for(long i = 0; i < pathSize; i++) {
            path+=cardPoint;
        }
        return path;        
    }
    
    /**
     * Generate a random walk with n (pathSize) cardinal points.
     * @param pathSize the number of cardinal points in the path.
     * @return path to walk.
     */
    public String randomWalk(long pathSize) {
        String path = "";
        for(long i = 0; i < pathSize; i++) {
            path+=randomCardPoint(0,cardinalPoints.length-1);
        }
        return path;
    }

    /**
     * Generate a quadrangular cycle path with an edge of n (diameter) cardinal
     * points with an outlier point.
     * @param diameter the number of cardinal points in an edge.
     * @return path to walk.
     */
    public String cycleWithOutlier(long diameter) {
        String path = "";
        String outlier = "SN";
        int numberOfCycles = 2; 
        for(int i = 0; i < numberOfCycles; i++) {
            path += cycle(diameter, cardinalPoints);
            path += outlier;            
        }
        return path;
    }
    
    /**
     * Generate a cycle path in eight shape, with an edge of n (diameter)
     * cardinal points.
     * @param diameter the number of cardinal points in an edge.
     * @return path to walk.
     */
    public String eightCycle(long diameter) {
        String path = "";
        String cardPointsOtherDirection[] = {"S","E","N","O"};
        path+=cycle(diameter, cardinalPoints);      
        path+=cycle(diameter, cardPointsOtherDirection);
        return path;
    }

    /**
     * Add an invalid cardinal to a valid line path with n (pathSize)
     * cardinal points. 
     * @param pathSize the number of cardinal points in the valid line path.
     * @return path to walk.
     */
    public String wrongInput(long pathSize) {
        String path = "A";
        path += line(pathSize);
        return path;
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
        ArrayList<String> testPaths =  new ArrayList<String>();
           
        testPaths.add("E");
        //testPaths.add("NESO");
        //testPaths.add("NSNSNSNSNS");
        //testPaths.add(testFileMaker.cycleWithOutlier(1000));
        //testPaths.add(testFileMaker.line(1000));
        //testPaths.add(testFileMaker.eightCycle(1000));
        //testPaths.add(testFileMaker.eightCycle(1000));
        //testPaths.add(testFileMaker.wrongInput(2000));


        //testPaths.add(testFileMaker.randomWalk(1000000));

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


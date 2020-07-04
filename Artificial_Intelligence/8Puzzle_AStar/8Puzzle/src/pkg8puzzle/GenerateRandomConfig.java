/*
 *  Copyright 2019.
 *  Alejadnro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */
package pkg8puzzle;

import java.util.ArrayList;
import Jama.Matrix;

/**
 * @author EAMT
 */
public class GenerateRandomConfig {
    // a will be shuffled
    public static String[] a = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
    public static String[][] goal = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", " "}};
    
    // To get a solution: (X^tX)^-1 X^tY
    public static double[] BETA_CONSTANTS = new double[3]; // Used for regression
    public static ArrayList<Double> YArray; // Contains the PATH-COSTs
    
    // To print with colors
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    // Generate max configurations
    public static String generateConfigurations(int max){
        YArray = new ArrayList<>();
        int n = 9;
        double one = 1, x2, x1; // to add to XArray for regression
       
        String[][] initialState; // a puzzle with the initial state
        ArrayList<String[][]> prob = new ArrayList<>(); // problem to be solved
        String[] randomConfig; // to store a random config based on "a"
        
        double[][] XArray = new double[max][3];
        
        for(int k = 0; k < max; k++){
            prob.clear(); // To add other problem, clearing the latest
            randomConfig = Fisher_Yates_Array_Shuffling.fisherYatesShuffling(a, n);   
            int invCount = Fisher_Yates_Array_Shuffling.getInvCount(randomConfig, n);
            
            if(invCount % 2 != 0) k--;
            else{
                // Transforming the array to a matrix
                initialState = arrayToMatrix(randomConfig); 
                //printMatrix(initialState);
                
                x1 = State.countDisplaces(initialState, goal); // Displaces tiles
                double totalNoAdjacents = State.countPairsNoAdjacents(initialState, goal);
                x2 = totalNoAdjacents;
               
                XArray[k][0] = one;
                XArray[k][1] = x1;
                XArray[k][2] = x2;

                prob.add(initialState);
                prob.add(goal);
                
                /* Searchs a solution: sending a problem, 
                    if you want to print the solution and which heuristic to use*/
                SearchSolution.uniform_cost_search(prob, false, 1, false);
                
                System.out.println((k+1) + ".-"
                    +"\tX=" + XArray[k][0] + "," + XArray[k][1] + "," + XArray[k][2]
                    +"\t\tY=" + YArray.get(k));
            }
        }
        
        BETA_CONSTANTS = obtainBetaConstants(XArray);
        if(BETA_CONSTANTS == null)
            return "Incomplete";
       
        
        return "Complete";
    }
      
    // Makes the regression and returns the 3 BETA coeficients
    public static double[] obtainBetaConstants(double[][] xArray){
        double[] yArr = new double[xArray.length];
        System.out.println("XArray.length: " + xArray.length);
        
        // Getting an Array instead of an ArrayList of YArray
        for(int i = 0;  i < YArray.size(); i++)
            yArr[i] = YArray.get(i);
            
        Matrix X = new Matrix(xArray);
        Matrix Y = new Matrix(yArr, yArr.length);
        Matrix XT = X.transpose();
        
        Matrix BETA;
        
        try{
            BETA = ((XT.times(X)).inverse()).times(XT).times(Y);
        }catch(Exception e){
            System.out.println(ANSI_RED + "\nError on BETA: " + e.toString() + 
                "\nDue to determinant of matrix is zero there is no inverse."
                + ANSI_RESET);
            BETA = new Matrix(0, 0);
        }
        
        if(BETA.getArray().length == 0)
            return null;
        
        System.out.println("\nBETA Consts:");
        BETA.print(12, 7);
        
        double[] b = {BETA.get(0, 0), BETA.get(1, 0), BETA.get(2, 0)};
        
        return b;
    }
    
    // Transform an array to Matrix and changes "0" by " "
    public static String[][] arrayToMatrix(String[] obj){
        int m = (int)Math.sqrt(obj.length);
        String my_puzzle[][] = new String[m][m];
        int cont = 0;

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < m; y++) {
                my_puzzle[x][y] = obj[cont];
                if("0".equals(my_puzzle[x][y]))
                    my_puzzle[x][y] = " ";
                cont++;
            }
        }
        return my_puzzle;
    }
    
    // Print z matrix
    public static void printMatrix(String[][] m){
        for (String[] m1 : m) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m1[j] + "\t");
            }
            System.out.println();
        }
            
    }
}
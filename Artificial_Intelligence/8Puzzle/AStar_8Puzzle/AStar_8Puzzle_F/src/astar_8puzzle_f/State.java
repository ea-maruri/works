/*
 *  Copyright 2019.
 *  Alejandro Maruri
 *  eamaruri@estud.usfq.edu.ec
 */

package astar_8puzzle_f;

import static java.lang.Math.ceil;

/**
 * @author EAMT
 */

public class State {
    public int f_n; // f(n) = h(n) + g(n)
    public int path_cost; // depth in search
    public String[][] puzzle; // puzzle as a matrix
    public int h_n;
	
    // Constructor. For each State calculates f(n) = manhattan() + path_cost
    public State(String[][] a, int depth, int heuristic){
        // Copying the matrix a to this.puzzle
        this.puzzle = new String[a.length][a.length];
        for (int i = 0; i < a.length; i++)
            System.arraycopy(a[i], 0, this.puzzle[i], 0, a.length);
	
        this.path_cost = depth;
        setFn(heuristic);
        //printFnAndPuzzle();
    }
    
    // Set f_n according to the given heuristic
    public final void setFn(int heuristic){
        switch (heuristic) {
            case 1:
                this.h_n = manhattan();
                this.f_n = h_n + this.path_cost;
                break;
            case 2:
                int b0 = (int) ceil(GenerateRandomConfig.BETA_CONSTANTS[0]);
                int b1 = (int) ceil(GenerateRandomConfig.BETA_CONSTANTS[1]);
                int b2 = (int) ceil(GenerateRandomConfig.BETA_CONSTANTS[2]);
                int x1 = countDisplaces(this.puzzle, SearchSolution.goalState);
                int x2 = countPairsNoAdjacents(this.puzzle, this.puzzle);
                
                this.h_n = (int) ceil((b0 + b1*x1 + b2*x2));
                this.f_n = h_n + this.path_cost;
                break;
            default:
                this.h_n = manhattan();
                this.f_n = h_n + this.path_cost;
                break;
        }
    }
    
    // Counts the displaced tiles. Compares a matrix with another
    public static int countDisplaces(String[][] init, String[][] goal){
        int N = init.length;
        
        int displaced = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
		if(init[i][j].trim().isEmpty()) continue;
				
		if (!init[i][j].trim().equals(goal[i][j])){
                    displaced++;
		}
            }
        }
        return displaced;
    }
    
    // Returns the sum of vertical and horizontal of pairs no adjacents
    public static int countPairsNoAdjacents(String[][] init, String[][] goal){
        return verticalPairsNoAdjacents(init, goal) +
               horizontalPairsNoAdjacents(init, goal);
    }
    
    // Count the vertical pairs no adjacents
    public static int verticalPairsNoAdjacents(String[][] init, String[][] goal){
        int count = 0;
        for(int i = 0;  i < init.length - 1; i++){
            for(int j = 0; j < init[i].length; j++){  
                if(!((init[i][j].equals(goal[i][j]) || init[i][j].equals(goal[i+1][j])) 
                   && (init[i+1][j].equals(goal[i][j]) || init[i+1][j].equals(goal[i+1][j])))){
                    count++;                            
                }
            }
        }
        return count;
    }
    
    // Count the horizontal pairs no adjacents
    public static int horizontalPairsNoAdjacents(String[][] init, String[][] goal){
        int count = 0;
        for(int i = 0;  i < init.length; i++){
            for(int j = 0; j < init[i].length - 1; j++){
                if(!((init[i][j].equals(goal[i][j]) || init[i][j].equals(goal[i][j+1])) 
                   && (init[i][j+1].equals(goal[i][j]) || init[i][j+1].equals(goal[i][j+1])))){
                    count++;                            
                }
            }
        }
        return count;
    }
    
    // Prints f(n) = h(n) + g(n); and then, the puzzle
    public final void printFnAndPuzzle(){
        System.out.print(String.format("f(n) = manhattan + depth: %d = %d + %d\n", 
                this.f_n, this.h_n, this.path_cost));
        printPuzzle();
        System.out.println();
    }
    
    /* Calculates the Manhattan distance heuristic for each node-state. 
        Sum of the distances of the tiles from their goal positions 
        (compares puzzle with goal state)*/
    public final int manhattan(){
	int sum = 0, N = SearchSolution.goalState.length;
	int[] pos;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
		if(this.puzzle[i][j].trim().isEmpty()) continue; // blanck no matter
		
                pos = findPosition(this.puzzle[i][j]);
                // Sum the difference between tile position with respect to its current position
                sum += (Math.abs(i - pos[0]) + Math.abs(j - pos[1]));
            }
	}
        return sum;
    }
    
    /* Finds the indices of a particular element in the goal state */
    private int[] findPosition(String a){
        int[] pos = new int[2];
	int N = SearchSolution.goalState.length;
	
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
		if(SearchSolution.goalState[i][j].trim().isEmpty()) continue;
				
		if (SearchSolution.goalState[i][j].trim().equals(a)){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
		}
            }
	}
        return pos;
    }
    
    // Prints the puzzle
    public void printPuzzle(){
        int N = SearchSolution.goalState.length;
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){
                System.out.print(this.puzzle[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
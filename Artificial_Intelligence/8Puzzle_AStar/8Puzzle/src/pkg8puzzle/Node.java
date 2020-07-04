/*
 *  Copyright 2019.
 *  Alejandro Maruri
 *  eamaruri@estud.usfq.edu.ec
 */

package pkg8puzzle;

import java.util.ArrayList;

/**
 * @author EAMT
 */
public final class Node implements Comparable<Node>{
    private State s;
    ArrayList<Node> children;
    
    // Constructor    
    public Node(State s){
        setState(s);
        children = new ArrayList<>();
    }
        
    // Getter and Setter
    public void setState(State s){this.s = s;}
    public State getState(){return this.s;}
    
    /* Generates all the possible child-nodes from a given parent-node 
            and return them as "children"
        Returns all possible moves from a specified state swapping its tiles
        Here are the actions of problem */
    public ArrayList<Node> expand(Node parent, int heuristic){
	int N = this.getState().puzzle.length;
	
        // Iterates for the configuration-state of the node
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                /* Search for the index of space (or zero) in the current state
                    (where a tile can be moved)*/
                if (parent.getState().puzzle[i][j].trim().isEmpty()){
                    String[][] a = new String[N][N];
                        
                    // Checks where a tile can be moved up
                    if(i - 1 >= 0){
			for (int l = 0; l < N; l++)
                            System.arraycopy(parent.getState().puzzle[l], 0, a[l], 0, N);
			
                        a = swapTile(a, i, j, i-1, j); // Tile moves up
			Node b = new Node(new State(a, parent.getState().path_cost+1, heuristic));
                        this.children.add(b);
                    }
		
                    // Checks whether a tile can be moved towards left side
                    if(j - 1 >= 0){
          		for (int l = 0; l < N; l++)
                            System.arraycopy(parent.getState().puzzle[l], 0, a[l], 0, N);
			
                        a = swapTile(a, i, j, i, j-1); // Tile moves left
			Node b = new Node(new State(a, parent.getState().path_cost+1, heuristic));
        		this.children.add(b);
                    }  
		
                    // Checks whether a tile can be moved down
                    if(i + 1 < N){
          		for (int l = 0; l < N; l++)
                            System.arraycopy(parent.getState().puzzle[l], 0, a[l], 0, N);
			
                        a = swapTile(a, i, j, i+1, j); // Tile moves down
			Node b = new Node(new State(a, parent.getState().path_cost+1, heuristic));
			this.children.add(b);
                    }
                    
                    //checks whether a tile can be moved towards right side
                    if(j + 1 < N){
			for (int l = 0; l < N; l++)
                            System.arraycopy(parent.getState().puzzle[l], 0, a[l], 0, N);
		
                        a = swapTile(a, i, j, i, j+1); // Tile moves right
			Node b = new Node(new State(a, parent.getState().path_cost+1,heuristic));
			this.children.add(b);
                    } 
		}
            }
	}
        return this.children; 
    }
    
    // Method that swaps the desired elements: Moves a tile
    private String[][] swapTile(String[][] a, int row1, int col1, int row2, int col2){
        String[][] copy = a;
        String tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }
    
    /* Comparing nodes according to its f(n) to accomodate in priority queue. 
        A sorting technique for the priority queue created in Solution class*/
    @Override
    public int compareTo(Node o) {
        // Returns the difference of h(n) of nodes, of its f(n) are equals
        if(this.getState().f_n == o.getState().f_n)
            return ((this.getState().manhattan() - o.getState().manhattan())); 
	
        // Returns the difference between f(n) of nodes
        return this.getState().f_n - o.getState().f_n;
    }
}
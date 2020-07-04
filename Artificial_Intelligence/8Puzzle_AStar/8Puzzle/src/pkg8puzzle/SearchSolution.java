/*
 *  Copyright 2019.
 *  Alejandro Maruri
 *  eamaruri@estud.usfq.edu.ec
 */

package pkg8puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author EAMT
 */
public class SearchSolution {
    // Definition of Structures for Uniform Cost Search
    public static Node node, goalNode; // Nodes that contain an associated state
    public static PriorityQueue<Node> frontier; // Priority Queue ordered by Path-Cost
    public static ArrayList<Node> explored; // Set of explored states
    public static String[][] initialState, goalState; // The goal state as a matrix
       
    public static double pathCost;
    
    // Gets initial and goal states as "problem"
    public static void uniform_cost_search(ArrayList<String[][]> problem, 
            boolean printSol, int heuristic, boolean printStat){
        /*Beggining with algorithm*/
        long startTime = System.currentTimeMillis();
	
        initSolution(problem, heuristic); // Initializin data structures for algorithm
        
        if(loopDo(heuristic).equals("Solution")){ // Here is the "loop do" of algorithm
            printSolution(printSol, printStat);
            GenerateRandomConfig.YArray.add(pathCost);
        }
        else
            System.out.println("Failure!!!");
	
        long endTime = System.currentTimeMillis();
        
        // adds each taken time of each solution to a total time
        Main.totalTime += (endTime - startTime); 
        
        if(printStat)
            System.out.println("*Time taken: " + (endTime - startTime) + "(ms)");
    }
     
    // Initialize data structure: node, frontier, explored, goal
    public static void initSolution(ArrayList<String[][]> problem, int heuristic){
        // Initializing states with the given configurations
        initialState = problem.get(0);  
        goalState = problem.get(1);
        
	node = new Node (new State(initialState, 0, heuristic)); // Node with the initial State of problem
        goalNode = new Node(new State(problem.get(1), 0, heuristic)); // Node with the goal state of problem
        frontier = new PriorityQueue<>(); // Priority Queue ordered by Path-Cost
        frontier.add(node); // The only element in priority Queue (first one)
        explored = new ArrayList<>(); // Empty set
    }
    
    // Has the "loop do" of the algorithm of search.
    public static String loopDo(int heuristic){
        ArrayList<Node> childrenNodes; // List that contains the expanded nodes
                
        // While there are nodes in the frontier. In the beginning the firs one
	while(true){
            if(frontier.isEmpty())
                return "Failure";
            
            /* Retrives and removes the first node of the priority queue
                chosses the lowest-cost node in frontier*/
            node = frontier.poll();
           
            /*if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)*/
            /* Checks if one the two arrays are deeply equals (state and goal): 
                contains the same number of elements and and each pair of 
                elements is deeply equal.
                We are in the goal state*/
            if(Arrays.deepEquals(node.getState().puzzle, goalNode.getState().puzzle)){ 
                explored.add(node); // Adding the goal node to solution
                return "Solution"; 
            }
            
            // Adds the current node to the end of the explored nodes
            explored.add(node); 
            
             /* For each action in problem.ACTIONS(node.STATE) 
                    do child ←CHILD-NODE(problem, node, action) 
            Expands the current node, stores it in childrenNodes*/
            childrenNodes = node.expand(node, heuristic);
            
            verifyChildrenNodes(childrenNodes); // if child.STATE is not in explored or frontier
	}
    }
    
    /*if child .STATE is not in explored or frontier then
        frontier ←INSERT(child , frontier )
      else if child .STATE is in frontier with higher PATH-COST then
        replace that frontier node with child*/
    public static void verifyChildrenNodes(ArrayList<Node> exp){
        boolean isExplored, isInFrontier;
        
        // Comparing if a child node (expanded node) is in frontier or in explored
        for(Node expandedNode : exp){
            isInFrontier = isExplored = false;
            
            // Checking in explored set
            for(Node exploredNode : explored)
                if(Arrays.deepEquals(expandedNode.getState().puzzle, exploredNode.getState().puzzle))
                    isExplored = true;
            
            // Checking in frontier
            for(Node inFrontierNode : frontier)
                if(Arrays.deepEquals(expandedNode.getState().puzzle, inFrontierNode.getState().puzzle))
                    isInFrontier = true;
	
            // Add to frontier if it is not in the explored or in the frontier
            if(!isExplored || !isInFrontier)              
                frontier.add(expandedNode); 
            
            /*else if child .STATE is in frontier with higher PATH-COST then
                replace that frontier node with child ???????????????????*/ 
            /*else if(expandedNode > )
                return;*/
        }    
    }
    
    // For each explored node, it prints the puzzle, and the f(n), h(n), and g(n) 
    public static void printSolution(boolean printSolution, boolean printStatistics){
        Node my_n = explored.get(explored.size() - 1);
        pathCost = my_n.getState().path_cost;
        
        if(printSolution){
            System.out.println("\n---SOLUTION---");
        
            // Solution is in the explored set
            explored.stream().map((n) -> {
                // Printing the puzzle
                System.out.println("\nNode " + (n.getState().path_cost + 1) 
                            + " in explored set:");
                n.getState().printPuzzle();
           
                System.out.println("f(n):" + n.getState().f_n 
                    + "\nh(n):" + (n.getState().f_n - n.getState().path_cost)
                    + "\ng(n):" + (n.getState().path_cost));
            
                return n;		
            }).forEachOrdered(i -> {}); // Ensuring to complete the action for each node
        }
        if(printStatistics){
            // We substract or sum ONE due to the first state (it does not count)
            int expandedNodes = explored.size();
            int generatedNodes = explored.size() + frontier.size();
	
            explored.get(0).getState().printPuzzle();
            System.out.println("\nStatistics"
                    + "\n*Total Nodes expanded: " + (expandedNodes - 1)
                    + " (does not include the initial node: " + expandedNodes + ")"
                + "\n*Total Nodes generated: " + (generatedNodes - 1) 
                    + " (does not include the goal and initial nodes: " 
                    + (generatedNodes + 1) + ")" 
                +"\n*Number of moves made: " 
                    + (explored.get(explored.size() - 1).getState().path_cost));
        }
    }
}   
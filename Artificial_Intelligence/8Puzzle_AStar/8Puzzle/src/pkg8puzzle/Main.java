/*
 *  Copyright 2020.
 *  ea.maruri@outlook.com
 */

package pkg8puzzle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author AM - GIK
 */
public class Main {
    public static long totalTime = 0;
    private static String[][] gState, iState; // The initial and goal state as a matrix
    private static ArrayList<String[][]> problem; // Stores the initial and the goal state
    static Main a = new Main(); // to use "generateProblem" method

    /**
     * Generate random problems and then call a function to ask for problems
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String canCreate = "Incomplete";
        int quantityOfConfigurations = 0;
        
        while(canCreate.equals("Incomplete")){
            // MINIMUM 3, cause there are 3 coeficients for BETA
            quantityOfConfigurations = a.askForQuantityOfConfigurations();
            System.out.println("\nCreating " + quantityOfConfigurations 
                    + " RANDOM Configurations... (it may take a few minutes)");
         
            // Generating a quantity of random configurations
            canCreate = GenerateRandomConfig.generateConfigurations(quantityOfConfigurations);
        }
        
        System.out.println("Time taken generating "+ quantityOfConfigurations
                + " RANDOM configurations: " + totalTime + "(ms)");
        
        begin(); // Asks for user to input problems
        System.out.println("\nProgram Teminated!!!");
    }
    
    
    /* Initializes the problem, asks for initial and goal states, 
        and if all is ok call to algorithm of search (uniform-cost-search)*/
    public static void begin() {
        System.out.println("\n\n----------------------------------------------------------");
        System.out.println("-- Welcome to A* informed search algorithm for 8-Puzzle --");
        System.out.println("----------------------------------------------------------");
           
        problem = new ArrayList<>(); // Problem to give to the search algorithm
        String createProblem; // To ask to a user to generate a problem
        int heuristic = 0; // Heuristic that will be used
       
        createProblem = a.askForCreateAProblem();
        if(createProblem.equals("y"))
            heuristic = a.askForHeuristic();
        if(createProblem.equals("e")){
            a.makeExample();
            heuristic = 1;
        }
        
        
        while(("y".equals(createProblem) || "e".equals(createProblem)) && (heuristic == 1 || heuristic == 2)){
            // Problem is changed each time
            if("y".equals(createProblem))
                problem = a.generateProblem(3); // Asks for states and store that in "problem"
            
            // If initial state or goal state are not null, then begin with algorithm
            if(!(iState == null) && !(gState == null)) {
                System.out.println("A problem has been sent!!! Heuristic: " + heuristic);
                SearchSolution.uniform_cost_search(problem, true, heuristic, true);
            }
            
            createProblem = a.askForCreateAProblem();
            if(createProblem.equals("y"))
                heuristic = a.askForHeuristic();
            if(createProblem.equals("e")){
                a.makeExample();
                heuristic = 1;
                iState = null;
                gState = null;
            }
        }
    }
    
    // Make the example
    public void makeExample(){
        System.out.println("Making Example");
        int heuristic = askForHeuristic();
        
        String[][] firstState = {{"1","2","3"}, {"4","6"," "}, {"7","5","8"}};
        String[][] secondState = {{"4","1","2"}, {"7","6","3"}, {" ","5","8"}};
        String[][] thirdState = {{"4", "1", "2"}, {"7","6","3"}, {"5","8"," "}};
        String[][] goalState = {{"1","2","3"}, {"4","5","6"}, {"7","8"," "}};
        
        Scanner in = new Scanner(System.in);
        System.out.print("Print Solution?(y/n): ");
        String printSol = in.next();
        boolean print = true;
        if(!printSol.equals("y"))
            print = false;
        
        ArrayList<String[][]> my_problem = new ArrayList<>();
       
        System.out.println("\nFirst Configuration: ");
        my_problem.add(firstState);
        my_problem.add(goalState);
        SearchSolution.uniform_cost_search(my_problem, print, heuristic, true);
        my_problem.clear();
        
        System.out.println("\nSecond Configuration: ");
        my_problem.add(secondState);
        my_problem.add(goalState);
        SearchSolution.uniform_cost_search(my_problem, print, heuristic, true);
        my_problem.clear();
        
        System.out.println("\nThird Configuration: ");
        my_problem.add(thirdState);
        my_problem.add(goalState);
        SearchSolution.uniform_cost_search(my_problem, print, heuristic, true);
        my_problem.clear();
    }
    
    // Asks to user how many of random configurations wants to create
    public int askForQuantityOfConfigurations(){
        Scanner input = new Scanner(System.in);
        String quantity = "2";
        
        while(Integer.parseInt(quantity) < 3){
            System.out.print("\nHow many random configurations do you want "
                    + "to create for learned heuristic?(n>=3): ");
            quantity = input.next();
            if(!isNumber(quantity)){
                System.out.println("Eror " + quantity + "is not a number!");
                quantity = "2";
            }
        }
        
        return Integer.parseInt(quantity);
    }
    
    // Asks to user which heuristic wants to use
    public int askForHeuristic(){
        Scanner input = new Scanner(System.in);
        String heuristic = " ";
        
        while(!"1".equals(heuristic) && !"2".equals(heuristic)){
            System.out.print("Which heuristic do you want to use? (1,2): ");
            heuristic = input.next();
        }
        
        a.showHeuristic(Integer.parseInt(heuristic));
        
        return Integer.parseInt(heuristic);
    }
    
    // Asks to user if he wants to create problem
    public String askForCreateAProblem(){
        Scanner input = new Scanner(System.in);
        String createProblem = " ";
       
        // Asking if user wants to generate a problem: initial and goal state
        while(!"y".equals(createProblem) && !"n".equals(createProblem) && !"n".equals(createProblem)){
            System.out.print("\nCreate problem (y/n) or use the Example (e)? (y,n,e): ");
            createProblem = input.next();
            
            if("n".equals(createProblem))
                return "n";
            else if("e".equals(createProblem))
                return "e";
        }
        return "y";
    }
    
    // Shows which heuristic will be used: Manhattan or Learned heuristic
    public void showHeuristic(int heuristic){
        switch (heuristic) {
            case 1:
                System.out.println("Using Manhattan");
                break;
            case 2:
                System.out.println("Using Learned Heuristic");
                break;
            default:
                System.out.println("By Default, using Manhattan");
                break;
        }
    }
            
    // Asks for initial and goal states and store these in "problem" ArrayList
    public ArrayList<String[][]> generateProblem(int dim){
        ArrayList<String[][]> prob = new ArrayList<>(); // To return
        
        // Asking for initial state
        iState = askForState("\nPlease input the Initial State (1-8 and single space instead of 0)", dim);
        if(iState == null) return null; // No asks for goal state
        
        // Asking for initial state
        gState = askForState("\nPlease input the Goal State (1-8 and single space insted of 0)", dim);
        if(gState == null) return null;
        
        // Adding states to return
        prob.add(iState);
        prob.add(gState);
    
        return prob;
    }
    
    // Asks for a state and returns ot as a matrix of String 
    public String[][] askForState(String message, int dim){
        System.out.println(message); 
        Scanner in = new Scanner(System.in); // To recieve each input for state
        String[][] state = new String[dim][dim];
        
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                state[i][j] = in.nextLine();
                // Validates the input provided and terminates for invalid input.
                if (incorrectValueInput(state[i][j])){
                    System.out.println("Error: Input should be any number"
                            + " between 1 to 8 or a single space: " + state[i][j]);
                    return null;
                }
            }
        }
        return state;
    }

    // Indicates if a string is a number
    private boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    // Checks if is an icorrect value for puzzle
    public boolean incorrectValueInput(String s){
        return s.length() != 1 || (s.charAt(0) < '1' && s.charAt(0) != ' ') 
            || s.charAt(0) > '8';
    }
}

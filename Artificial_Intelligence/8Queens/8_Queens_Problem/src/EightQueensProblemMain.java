/**
 * 8 Queens Problem
 *
 * @author Alejandro Maruri
 * @author Joel Maldonado
 * */

public class EightQueensProblemMain {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();

        int numberOfChromosomes = 30;
        Population population = new Population(numberOfChromosomes); // Creates a population with 30 random Chromosomes
        System.out.printf("A population of %d chromosomes has been created ...%n%n",  numberOfChromosomes);

        GA ga = new GA(); // Object of Genetic Algorithm
        System.out.println("\nBEST SOLUTION found: " + ga.genetic_algorithm(population, 28).toString());

        long endTime = System.currentTimeMillis();
        System.out.println("\nTotal taken time: " + (endTime - startTime) + "(ms).");
    }
}

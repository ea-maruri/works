import java.util.Arrays;
import java.util.Random;

/**
 * Chromosome class. Represents a state of an 8-Queens puzzle (the row where is a queen in each column)
 *
 * @author Alejandro Maruri
 * @author Joel Maldonado
 * */

public class Chromosome implements Comparable<Chromosome> {
    private Integer[] chromosome; // The state of the 8 queen puzzle as an array of Integers
    private int fitnessFunction; // Pairs of non attacking queens
    private boolean mutated; // Indicates if a Chromosome is mutated or not

    // Constructor, generates a Chromosome with a given array of Integers
    Chromosome(Integer[] c){
        this.chromosome = c;
        setFitnessFunction();
        this.mutated = false;
    }

    // Constructor, generates a random configuration
    Chromosome(){
        //Integer[] c1 = createRandomChromosome(); // Generates more attacks (horizontal). It is better???
        Integer[] c = {1,2,3,4,5,6,7,8};
        this.chromosome = Fisher_Yates_Array_Shuffling.fisherYatesShuffling(c, c.length); // Random configuration.
        setFitnessFunction();
        this.mutated = false;
    }

    // Call to pairsOfNonAttackingQueens method
    private void setFitnessFunction(){
        this.fitnessFunction = pairsOfNonAttackingQueens();
    }
    public void setMutated(boolean mutated){this.mutated = mutated;}

    // Getters
    public int getFitnessFunction(){return this.fitnessFunction;}
    public Integer[] getChromosome(){return this.chromosome;}

    // Count the number of pairs of non attacking queens
    private int pairsOfNonAttackingQueens(){
        int attacks = 0, dx, dy;

        // Comparing pairs
        for (int i = 1; i <= this.chromosome.length; i++){
            int j = i + 1; // comparing with the next one
            for(; j <= this.chromosome.length; j++){

                // Horizontal
                if(this.chromosome[i-1].equals(this.chromosome[j-1])) attacks++;

                //diagonal
                dx = Math.abs(i-j);
                dy = Math.abs(this.chromosome[i-1]-this.chromosome[j-1]);

                if(dx == dy) attacks++;
            }
        }
        // Subtracts the maximum of attacks with the number of the counted attacks (This is the non attacking queens)
        return 28 - attacks;
    }

    // Generates a random configuration of a state to have queens that attack them in horizontal
    private Integer[] createRandomChromosome(){
        Random r = new Random();
        Integer[] c = new Integer[8];

        for(int i = 0; i < c.length; i++)
            c[i] = r.nextInt(8) + 1;

        return c;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (Integer integer : this.chromosome)
            sb.append(integer).append("\t");

        sb.append("\tfitness function: ").append(this.getFitnessFunction());

        if(this.mutated)  sb.append("\t(mutated)");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o){return Arrays.deepEquals(this.chromosome, ((Chromosome) o).getChromosome());}

    @Override
    public int compareTo(Chromosome c) {return Integer.compare(c.getFitnessFunction(), this.fitnessFunction);}
}

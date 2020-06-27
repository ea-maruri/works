import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class GA {
    private SecureRandom myRandom = new SecureRandom();
    private int numGeneration = 0;

    /**
     * @param population a set of individuals
     * @return individual, an individual (chromosome)
     * */
    public Chromosome genetic_algorithm(Population population, int fitness_function){
        PriorityQueue<Chromosome> pq = new PriorityQueue<>(population.getChromosomes());
        Chromosome bestIndividual = pq.peek();
        // System.out.println("\nBEST individual:" + bestIndividual);

        Population new_population;

        while (true){
            new_population = new Population(0); // An empty set

            for (int i = 0; i < population.getSize(); i++){
                Chromosome x = Random_Selection(population);
                Chromosome y = Random_Selection(population);

                // If x and y are different then we can add a NEW child
                if(!x.equals(y)){
                    Chromosome child = Reproduce(x, y);

                    //System.out.println("x: " + x + "\ny: " + y + "\nChild: " + child);

                    if(child.getFitnessFunction() == fitness_function) {
                        System.out.println("\nIn generation " + numGeneration + " was found a solution");
                        return child;
                    }

                    double srp = myRandom.nextFloat();
                    if(srp < 0.01)
                        mutate(child);

                    new_population.getChromosomes().add(child);

                    //if(new_population.getSize() == 30)
                      //  System.out.println("Pop:\n" + new_population);
                    if(child.getFitnessFunction() >= bestIndividual.getFitnessFunction())
                        bestIndividual = child;
                }
                else i--;
            }

            if(++numGeneration == 200000) {
                PriorityQueue<Chromosome> my_pq = new PriorityQueue<>(new_population.getChromosomes());
                System.out.println("Individual in population: " + my_pq.peek());
                return bestIndividual;
            }
        }
    }

    // Calls to Roulette_Wheel_Selection_Algorithm
    private Chromosome Random_Selection(Population population){
        return Roulette_Wheel_Selection_Algorithm(population);
    }

    // Reproduces two chromosomes and returns one
    private Chromosome Reproduce(Chromosome x, Chromosome y){
        int n = x.getChromosome().length;
        int c = 8;

        // Do not choose the same chromosome
        while (c == 8)
            c = (int) ((Math.random() * n) + 1); // Random number from 1 to n

        Integer[] sub1 = Arrays.copyOfRange(x.getChromosome(), 0, c);
        Integer[] sub2 = Arrays.copyOfRange(y.getChromosome(), c, n);
        Integer[] result = ConcatenateArrays(sub1, sub2);

        return new Chromosome(result);
    }

    // Implements the “Roulette Wheel Selection Algorithm” to select an individual (chromosome)
    private Chromosome Roulette_Wheel_Selection_Algorithm(Population population){
        double r = myRandom.nextDouble();
        double sum = 0;

        for(Chromosome i : population.getChromosomes()){
            sum += P(i, population.getChromosomes());
            if(r < sum) {
                //System.out.println("Roullette: " + i);
                return i;
            }
        }
        return null;
    }

    // Calculates the probability of choose a Chromosome
    private double P(Chromosome chromosome, ArrayList<Chromosome> chromosomes){
        double fi = chromosome.getFitnessFunction();
        double sumFi = 0;

        for(Chromosome c : chromosomes)
            sumFi += c.getFitnessFunction();

        return fi / sumFi;
    }

    // Mutates a Chromosome
    private void mutate(Chromosome c){
        int position = myRandom.nextInt(8) + 1;
        c.getChromosome()[position - 1] = (myRandom.nextInt(8) + 1);
        c.setMutated(true);
    }

    // Concatenate two arrays
    private Integer[] ConcatenateArrays(Integer[] sub1, Integer[] sub2) {
        Integer[] result = new Integer[sub1.length + sub2.length];

        System.arraycopy(sub1, 0, result, 0, sub1.length);
        System.arraycopy(sub2, 0, result, sub1.length, sub2.length);

        return result;
    }
}

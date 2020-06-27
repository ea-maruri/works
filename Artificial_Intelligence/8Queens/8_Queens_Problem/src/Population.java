import java.util.ArrayList;

/**
 * Population class. It is an ArrayList of Chromosome objects
 *
 * @author Alejandro Maruri
 * */

public class Population {
    private ArrayList<Chromosome> chromosomes;

    // Constructor, generates a random population of chromosomes
    Population(int n){
        setChromosomes(n);
    }

    // Setters
    private void setChromosomes(int n){
        this.chromosomes = new ArrayList<>();
        for(int i = 0;  i < n; i++)
            this.chromosomes.add(new Chromosome());
    }

    // Getters
    public int getSize(){ return this.chromosomes.size(); }
    public ArrayList<Chromosome> getChromosomes(){ return this.chromosomes; }

    @Override
    public String toString(){
        StringBuilder sb =  new StringBuilder("");

        int i = 1;
        for(Chromosome c: this.chromosomes)
            sb.append(i++).append(":\t").append(c.toString()).append('\n');

        sb.append("\n\tSize of population: ").append(this.chromosomes.size());
        return sb.toString();
    }
}

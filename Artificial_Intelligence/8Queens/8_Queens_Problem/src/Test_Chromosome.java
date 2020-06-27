public class Test_Chromosome {

    // Testing the class Chromosome and fitness function
    public void test(){
        Integer[] my_ints = {2,4,7,4,8,5,5,2};
        Integer[] my_ints1 = {3,2,7,5,2,4,1,1};
        Integer[] my_ints2 = {2,4,4,1,5,1,2,4};
        Integer[] my_ints3 = {3,2,5,4,3,2,1,3};

        Chromosome chromosome = new Chromosome(my_ints);
        Chromosome chromosome1 = new Chromosome(my_ints1);
        Chromosome chromosome2 = new Chromosome(my_ints2);
        Chromosome chromosome3 = new Chromosome(my_ints3);

        // Fitness function: 24, 23, 20, 11
        System.out.println(chromosome.toString());
        System.out.println(chromosome1.toString());
        System.out.println(chromosome2.toString());
        System.out.println(chromosome3.toString());
    }
}

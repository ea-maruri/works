package deber6_avltree;

/**
 * @author Alejandro Maruri
 */
public class Deber6_AVLTree {

     public static void main(String[] args) {
        // ALL code application logic here
        
        AVLTreeMap<Integer,cStudent> studentsAVL = new AVLTreeMap<>(); //Instance of AVLTree
        int keyToRemove;
        //Position<AVLTreeMap> pos = new Position<AVLTreeMap>() {};
        
        //tESTING ISeMPTY
        if(studentsAVL.isEmpty())
            System.out.println("In the beginning AVL is empty (size is " + studentsAVL.size() + ").\n"
                    + "Code of student is the key of studentsAVL\n");
        
        //DATA in Arrays
        String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack", "Anna", "Paul", "Bob"}; //names
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "James", "Taylor", "Adams", "Wilson"}; //lastNames
        int[] ages = {21, 20, 25, 19, 22, 20, 24, 22}; // ages
        int[] codes = {111, 222, 333, 444, 555, 666, 777, 888}; // codes
        String[] semesters = {"First", "Fourth", "Tenth", "Fifth", "Eigth", "Sixth", "Seventh", "Second"}; // semesters
        int[] credits = {23, 85, 175, 92, 120, 100, 105, 42}; // credits
        
        //System.out.println("Inserting...");
        for(int i = 0; i < names.length; i++){
            cStudent stud = new cStudent(names[i], lastNames[i], ages[i], codes[i], semesters[i], credits[i]);
            studentsAVL.put(codes[i], stud);
            System.out.println("Inserting... " + (i+1) + " student"); //putting in AVL
        }
            
        System.out.println("\nNow size of AVL is: " + studentsAVL.size());
        keyToRemove = 333;
        System.out.println("\nRemoving value with key: " + keyToRemove + "...\n"
                + "\tThe student removed was:\n" + studentsAVL.remove(keyToRemove));
        keyToRemove = 777;
        System.out.println("Removing value with key: " + keyToRemove + "...\n"
                + "\tThe student removed was:\n" + studentsAVL.remove(keyToRemove));
        keyToRemove = 333; //This key already doesn't exist
        System.out.println("Removing value with key: " + keyToRemove + "...\n"
                + "\tThe student removed was:\n" + studentsAVL.remove(keyToRemove));
        
        System.out.println("\nNow size of AVL is: " + studentsAVL.size());
        
        System.out.println("\nThe first Entry in the studentsAVL is:\n " + studentsAVL.firstEntry());
        
        System.out.println("\nThe last Entry in the studentsAVL is:\n " + studentsAVL.lastEntry());
        
        cStudent stud1 = new cStudent(names[2], lastNames[2], ages[2], codes[2], semesters[2], credits[2]);
        studentsAVL.put(codes[2], stud1);
        System.out.println("\nInserting...\nThe value inserted is: \n" + stud1.toString());
       
        System.out.println("Now size of AVL is: " + studentsAVL.size());
        //System.out.println(studentsAVL.toString());
    }
}
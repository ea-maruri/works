package deber4_linkedbinarysearchtree1;
/**
 * @author Alejandro Maruri
 */
public class Deber4_LinkedBinarySearchTree1 {
    public static void main(String[] args) {
        //LinkedBinarySearchTree of cStudent
        LinkedBinarySearchTree<cStudent> studTree = new LinkedBinarySearchTree<>();
                    
        //Arrays of data
        String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack", "Anna", "Paul", "Bob"}; //names
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "James", "Taylor", "Adams", "Wilson"}; //lastNames
        int[] ages = {21, 20, 25, 19, 22, 20, 24, 22}; // ages
        int[] codes = {111, 222, 333, 444, 555, 666, 777, 888}; // codes
        String[] semesters = {"First", "Fourth", "Tenth", "Fifth", "Eigth", "Sixth", "Seventh", "Second"}; // semesters
        int[] credits = {23, 85, 175, 92, 120, 100, 105, 42}; // credits
        
        if(studTree.isEmpty())
            System.out.println("In the beginnig Tree is Empty\n");
        
        int j = 1;
        for (int i = 0; i < names.length; i++) {
            cStudent stud = new cStudent(names[i], lastNames[i], ages[i], codes[i], semesters[i], credits[i]);
            System.out.println("Adding student " + j + "...");
            if(studTree.isEmpty()){ // Free tree, then is root
                System.out.println("This student is root:\n" + stud.toString());
            }
            studTree.add(stud);// Adding in Tree
            j++; // Counting added elements
        }
        
        System.out.println("\nLinked Binary Search Tree in order (by last name):\n" + studTree.toString());
        
        if(!studTree.isEmpty())
            System.out.println("\nNow Tree is no Empty\nSize of Linked Binary Search Tree: " + studTree.size);
        //Printing element in root
        System.out.println("\nRoot:\n" + studTree.root().getElement().toString());
        
        //Removing a student
        cStudent stud1 = new cStudent(names[6], lastNames[6], ages[6], codes[6], semesters[6], credits[6]);
        studTree.remove(stud1);
        studTree.remove(stud1); // No more stud1. Must be no found
        cStudent stud2 = new cStudent(names[2], lastNames[2], ages[2], codes[2], semesters[2], credits[2]);
        studTree.remove(stud2);
        
        //toString of Linked Binary Search Tree
        System.out.println("\nLinked Binary Search Tree in order (by last name):\n" + studTree.toString());
        
        /*Testing method contains*/
        if(studTree.contains(stud2)){
            System.out.println("\nLinked Binary Search Tree contains\n" + stud2.toString());
            studTree.remove(stud2);
        }
        else
            System.out.println("\nLinked Binary Search Tree do not contains\n" + stud2.toString());
        
        cStudent stud3 = new cStudent(names[2], lastNames[2], ages[2], codes[2], semesters[2], credits[2]);
        studTree.add(stud3);
        
        if(studTree.contains(stud3))
            System.out.println("\nLinked Binary Search Tree contains\n" + stud3.toString());
        else
            System.out.println("\nLinked Binary Search Tree do not containsn" + stud2.toString());
    }    
}
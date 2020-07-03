package deber3_orderedlists;

import static deber3_orderedlists.InsertionSort.*;
import java.util.Iterator;

/**
 * @author Alejandro Maruri
 */
public class Deber3_OrderedLists {

    public static void main(String[] args) {
        
        //Instances of LinkedPositionalList. Are Positional Lists
        LinkedPositionalList<Integer> list1 = new LinkedPositionalList<>();
        LinkedPositionalList<Integer> list2 = new LinkedPositionalList<>();
        LinkedPositionalList<Integer> list3 = new LinkedPositionalList<>();
                
        double num1, num2; // Random numbers
        int n1; //Number to print
        
        for(int i = 0; i < 15; i++){ //15 random numbers
            num1 = Math.random() * 50 + 1; //Numbers from 1 to 50
            num2 = Math.random() * 50 + 1;
            list1.addFirst((int)num1); //Add in list1. Cast of random num ehis is double
            list2.addFirst((int)num2);
        }
        
        /*Printing lists*/
        System.out.println("List 1:\n");
        for(Iterator<Integer> it = list1.iterator(); it.hasNext();){
            n1 = it.next();
            System.out.print(n1 + " ");
        }
        
        System.out.println("\n\nList 2:\n");
        for(Iterator<Integer> it = list2.iterator(); it.hasNext();){
            n1 = it.next();
            System.out.print(n1 + " ");
        }
        
        /*Sorting Lists*/
        insertionSort(list1); //Static method of insertion sort
        insertionSort(list2);
        
        /*Printing new lists*/
        System.out.println("\n\n\nSorted List 1:\n"); 
        for(Iterator<Integer> it = list1.iterator(); it.hasNext();){
            n1 = it.next();
            System.out.print(n1 + " ");
        }
        
        System.out.println("\n\nSorted List 2:\n");
        for(Iterator<Integer> it = list2.iterator(); it.hasNext();){
            n1 = it.next();
            System.out.print(n1 + " ");
        }
        
        System.out.println("\n\n\nList3:\n");
        
        list3 = intercalate(list1,list2); //Intecalary List1 with List2 and sort it.
        
        for(Iterator<Integer> it = list3.iterator(); it.hasNext();){
            n1 = it.next();
            System.out.print(n1 + " ");
        }
    }
}
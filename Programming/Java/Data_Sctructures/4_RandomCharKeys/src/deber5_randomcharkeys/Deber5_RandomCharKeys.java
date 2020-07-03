package deber5_randomcharkeys;

import java.util.Scanner;

/**
 * @author Alejandro Maruri
 */
public class Deber5_RandomCharKeys {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Insert a positive number (x10): ");
        
        int N = in.nextInt();
        cRandomCharKey RandomCharsKey;
                
        if(N == 0)
            RandomCharsKey = new cRandomCharKey(1);
        else
            RandomCharsKey = new cRandomCharKey(N); //Instance
        /*Constructor of cRandomCharKey make all.
        The class has all the methods. 
        */
    }    
}
/*
 *  Copyright 2019.
 *  Alejadnro Maruri.
 *  ale_maruri14@hotmail.com
 *  eamaruri@estud.usfq.edu.ec
 */
package astar_8puzzle_f;

import java.util.Random;

/**
 *
 * @author dfellig
 */
public class Fisher_Yates_Array_Shuffling {
   
    // Creates a random Array given an array
    public static String[] fisherYatesShuffling(String[] arr, int n) {
        String[] a = new String[n];
        
        int[] ind = new int[n];
        for (int i = 0; i < n; i++) 
            ind[i] = 0;
        
        int index;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            do {
                index = rand.nextInt(n);
            } while (ind[index] != 0);

            ind[index] = 1;
            a[i] = arr[index];
        }
        return a;
    }
     
    /* Counts the number of inversions. Iterate among an array. 
    It needs to compare that charAt(0) it is nonzero (this counts an inversion)*/
    public static int getInvCount(String arr[], int n) {
        int inv_count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j].charAt(0) != '0' && arr[i].charAt(0) != '0' 
                        && arr[i].charAt(0) > arr[j].charAt(0)) {
                    inv_count++;
                }
            }
        }
        return inv_count;
    }
}
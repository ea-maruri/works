package com.eamt;

import java.util.LinkedList;

public class cBank {
    private int typeNum;
    private int processNum;
    private static boolean findSol = false;

    // Getters
    public int getProcessNum() {
        return this.processNum;
    }
    public int getTypeNum() {
        return this.typeNum;
    }

    //Setters
    public void setProcessNum(int processNum) { this.processNum = processNum; }
    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }


    public void printData(int[] total, int[][] max, int[][] allocation){
        //System.out.println("\nnBank has started...");
        int[] available = new int[typeNum];

        for(int i = 0; i < typeNum; i++){
            available[i] = total[i];

            for(int j = 0; j < processNum; j++){
                available[i] -= allocation[j][i];
            }
        }

        int[][] need = new int[processNum][typeNum];

        for(int i = 0; i < processNum; i++){
            for(int j = 0; j < typeNum; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        System.out.println("Total system resources are: ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print(c + "\t");
        }
        System.out.println();

        for(int resource : total){
            System.out.print(resource + "\t");
        }
        System.out.println("\n");

        System.out.println("Available system resources are: ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print(c + "\t");
        }
        System.out.println();

        for(int resource : available){
            System.out.print(resource + "\t");
        }
        System.out.println("\n");

        System.out.println("Processes (maximum resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++) System.out.print("\t" + c);
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(max[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Processes (currently allocated resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + c);
        }
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(allocation[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Processes (possibly needed resources): ");

        for(char c = 'A'; c < 'A' + typeNum; c++){
            System.out.print("\t" + c);
        }
        System.out.println();

        for(int i = 0; i < processNum; i++){
            System.out.print("P" + (i+1) + "\t");
            for(int j = 0; j < typeNum; j++){
                System.out.print(need[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Determining safe sequence");

        LinkedList<Integer> safeSequence = new LinkedList<>();
        boolean[] takenProcess = new boolean[processNum];

        //safeSequence(takenProcess, allocation, max, need, available, safeSequence);
    }

    // Function to find the system is in safe state or not
    public boolean is_secure(int[] processes, int[] avail, int[][] maxm, int[][] allot) {
        int[][] need = new int[this.processNum][this.typeNum];

        // Function to calculate need matrix
        calculate_need(need, maxm, allot);

        // Mark all processes as infinish
        boolean[] finish = new boolean[this.processNum];

        // To store safe sequence
        int[] safeSeq = new int[this.processNum];

        // Make a copy of available resources
        int[] work = new int[this.typeNum];
        for (int i = 0; i < this.typeNum ; i++)
            work[i] = avail[i];

        // While all processes are not finished
        // or system is not in safe state.
        int count = 0;
        while (count < this.processNum) {
            // Find a process which is not finish and
            // whose needs can be satisfied with current
            // work[] resources.
            boolean found = false;
            for (int p = 0; p < this.processNum; p++) {
                // First check if a process is finished,
                // if no, go for next condition
                if (finish[p] == false) {
                    // Check if for all resources of
                    // current P need is less
                    // than work
                    int j;
                    for (j = 0; j < this.typeNum; j++)
                        if (need[p][j] > work[j])
                            break;

                    // If all needs of p were satisfied.
                    if (j == this.typeNum) {
                        // Add the allocated resources of
                        // current P to the available/work
                        // resources i.e.free the resources
                        for (int k = 0; k < this.typeNum; k++)
                            work[k] += allot[p][k];

                        // Add this process to safe sequence.
                        safeSeq[count++] = p;

                        // Mark this p as finished
                        finish[p] = true;

                        found = true;
                    }
                }
            }

            // If we could not find a next process in safe
            // sequence.
            if (found == false)
            {
                System.out.print("System is not in safe state");
                return false;
            }
        }

        // If system is in safe state then
        // safe sequence will be as below
        System.out.print("System is in safe state.\nSafe"
                +" sequence is: ");
        for (int i = 0; i < this.processNum ; i++)
            System.out.print(safeSeq[i] + " ");

        return true;
    }

    // Function to find the need of each process
    private void calculate_need(int need[][], int maxm[][], int allot[][]) {
        // Calculating Need of each P
        for (int i = 0 ; i < this.processNum ; i++) {
            for (int j = 0; j < this.typeNum; j++) {
                // Need of instance = maxm instance -
                //                 allocated instance
                need[i][j] = maxm[i][j] - allot[i][j];
            }
        }
    }
}

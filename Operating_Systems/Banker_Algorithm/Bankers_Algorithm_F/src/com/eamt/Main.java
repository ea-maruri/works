package com.eamt;

import java.util.Arrays;

public class Main {
    private static int number_of_processes;
    private static int[] processes_array;
    private static int types_of_resources;
    private static int[] available_instances;
    private static int[][] max_instances_for_request;
    private static int[][] assigned_instances;

    public static void main(String[] args) {
        //args = args.split("&");
        String[] splitted_args;
        if(args.length == 1){
            splitted_args = args[0].split("&");
            //System.out.println(Arrays.toString(splitted_args));
        }
        else{
            usage_error("Error in the given argument. Must be one separated by \"&\"");
            return;
        }
        System.out.println();

        if(splitted_args.length == 5){
            assign_arguments(splitted_args);

           cBank bank = new cBank();
           bank.setProcessNum(number_of_processes);
           bank.setTypeNum(types_of_resources);

            System.out.println("\n");
            bank.printData(available_instances, max_instances_for_request, assigned_instances);
           //bank.start(available_instances, max_instances_for_request, assigned_instances);

           if(bank.is_secure(processes_array, available_instances, max_instances_for_request, assigned_instances)){
               System.out.println("\nSECURE!!!");
           }
           else{
               System.out.println("\nINSECURE!!!");
           }


        }
        else{
            usage_error("in main. Number or argumens is distinct of 5");
            return;
        }
    }

    //
    private static void assign_arguments(String[] args){
        System.out.println("Received arguments:");
        for(String arg : args){
            System.out.println("Arg.- " + arg);
        }

        System.out.println();
        number_of_processes = Integer.parseInt(args[0]); // int
        if(number_of_processes > 0) {
            System.out.println("-Processes: " + String.valueOf(number_of_processes));
            processes_array = new int[number_of_processes];
            for (int i = 0; i < number_of_processes; i++) {
                processes_array[i] = i;
            }
        }
        else{
            usage_error("Number of processes must be positive");
            return;
        }


        types_of_resources = Integer.parseInt(args[1]); // int
        System.out.println("-Types of resources: " + String.valueOf(types_of_resources));

        // Array of ints with types_of_resources length
        String[] availables = args[2].split(",");
        if (availables.length == types_of_resources) {
            available_instances = new int[availables.length];
            for(int i = 0; i < available_instances.length; i++) {
                available_instances[i] = Integer.parseInt(availables[i]);
            }
        }
        else{
            usage_error("in availables");
            System.out.println(String.valueOf(availables.length) + " != " + String.valueOf(types_of_resources));
            return;
        }
        System.out.println("-Available instances:");
        System.out.print('\t');
        for(int i : available_instances){
            System.out.print(String.valueOf(i) + '\t');
        }
        System.out.println();

        // Matrix of ints with types_of_resources as columns and processes as rows
        String[] rows_for_request = args[3].split("-");
        if (rows_for_request.length == number_of_processes) {
            max_instances_for_request = new int[number_of_processes][types_of_resources];
            for(int i = 0; i < rows_for_request.length; i++){
                String[] columns_for_request = rows_for_request[i].split(",");
                if(columns_for_request.length == types_of_resources){
                    for(int j = 0; j < columns_for_request.length; j++){
                        max_instances_for_request[i][j] = Integer.parseInt(columns_for_request[j]);
                    }
                }
                else{
                    usage_error("in for_request (columns) --> " + String.valueOf(columns_for_request.length) + " != " + String.valueOf(types_of_resources));
                    return;
                }
            }
        }
        else{
            usage_error("in for_request (rows) -->" + String.valueOf(rows_for_request.length) + " != " + String.valueOf(number_of_processes));
            return;
        }
        System.out.println("-Max instances for request:");
        print_matrix(max_instances_for_request);

        // Matrix of ints with types_of_resources as columns and processes as rows
        String[] rows_for_assigned = args[4].split("-");
        if (rows_for_assigned.length == number_of_processes) {
            assigned_instances = new int[number_of_processes][types_of_resources];
            for(int i = 0; i < rows_for_assigned.length; i++){
                String[] columns_for_assigned = rows_for_assigned[i].split(",");
                if(columns_for_assigned.length == types_of_resources){
                    for(int j = 0; j < columns_for_assigned.length; j++){
                        assigned_instances[i][j] = Integer.parseInt(columns_for_assigned[j]);
                    }
                }
                else{
                    usage_error("in assigned (columns) --> " + String.valueOf(columns_for_assigned.length) + " != " + String.valueOf(types_of_resources));
                    return;
                }
            }
        }
        else{
            usage_error("in assigned (rows) -->" + String.valueOf(rows_for_request.length) + " != " + String.valueOf(number_of_processes));
            return;
        }
        System.out.println("-Assigned instances:");
        print_matrix(assigned_instances);
    }


    private static void usage_error(String message){
        System.err.println("Error: " + message);
        System.err.println("usage: process&type_of_resources&available_instances&max_instances_for_request&assigned_instances" +
                "\n\tprocesses (as int)." +
                "\n\ttype_of_resources (as int)." +
                "\n\tavailable_instances (as string separated by commas to make an array)." +
                "\n\tmax_instances_for_request (as string separated by commas and dash to make a matrix)." +
                "\n\tassigned_instances (as string separated by commas and dash to make a matrix).");
        System.err.println("\nExample1: 5&3&3,3,2&7,5,3-3,2,2-9,0,2-2,2,2-4,3,3&0,1,0-2,0,0-3,0,2-2,1,1-0,0,2");
        System.out.println("\nExample2: 5&4&3,0,1,1&2,1,4,4-2,1,2,2-1,2,2,2-2,0,0,1-1,1,1,1&2,1,2,2-4,0,2,1-1,3,2,1-1,1,1,0-2,0,2,1");
    }

    private static void print_matrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print('\t' + String.valueOf(matrix[i][j]) + '\t');
            }
            System.out.println();
        }
    }
}

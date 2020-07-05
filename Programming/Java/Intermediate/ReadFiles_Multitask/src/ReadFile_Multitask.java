//package readtext_multitask;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author EAMT
 * Reference: https://stackoverflow.com/questions/43499976/how-to-cut-a-string-into-1-megabyte-substring-with-java
 */
class ReadText_MultiTask {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException // Maybe there is no arguments in args[]
     */
    public static void main(String[] args) throws IOException, Exception {
        long tBegin, tEnd, time;
        tBegin = System.currentTimeMillis();

        String file = args[0];
        //"C:\\Users\\Alejandro Maruri\\Downloads\\file3_5.txt"
        cReadText reader = new cReadText();
        ExecutorService executor = Executors.newCachedThreadPool(); //Manage threads

        String toPrint = String.format("\n\t\t\t%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c"
                        + "%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c"
                        + "%-8c%-8c%-8c%-8s%-8s\t%-8s\n",'A','B','C','D','E','F','G','H',
                'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',
                'Y','Z', "Marks", "Bytes", "Time [ms]");

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tFREQUENCY OF CHARS\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\tMulti-Thread\n\n"
                +"Size of File: " + reader.sizeOfFile(file) + " b.\n"
                + "Quantity of Threads: "
                + reader.makeSubStrings((reader.readTextToString(file)), 500000, "UTF-8").size());

        System.out.printf(toPrint);

        Thread task; //Reference to task. Is a Thread
        
        /*For each piece of string make a task
        Substirngs of the string of file. */
        for(String piece : reader.makeSubStrings((reader.readTextToString(file)), 500000, "UTF-8")){
            task = new Thread(new cFrequencyCharsTask(piece, "FrequencyOfChars.txt"));
            task.join();
            executor.execute(task);
        }
        executor.shutdown();

        tEnd = System.currentTimeMillis();
        time = tEnd - tBegin;
        System.out.println("\n\nTotal time of execution: " + time + "[ms]");
    }
}
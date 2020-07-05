//package readtext_multitask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author EAMT
 */

public class cFrequencyCharsTask implements Runnable {

    private class cPair {
        private String key;
        private long value;

        cPair(String key, long value){
            this.key = key;
            this.value = value;
        }

        public String getKey(){return key;}
        public long getValue(){return value;}

        @Override
        public String toString(){
            return String.format("\t%s", getValue());
        }
    }

    private String stringToCount;
    private String nameTask;
    private Map<String, Long> frequency;
    private static List<cPair> listOfTotals;
    private List<cPair> allPossiblePairs;
    private long beginTime;
    private File fileToWrite;
    private static FileWriter writer;
    private static StringBuffer toWriteInFile;
    private static final SecureRandom generator = new SecureRandom();
    private long time = 0;
    private long newTime = 0;

    /*Constructor*/
    public cFrequencyCharsTask(String pStringToCount, String nameOfFile) throws IOException{
        fileToWrite = new File(nameOfFile);

        String toPrint = String.format("\r\r\t\t\t%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c"
                        + "%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c%-8c"
                        + "%-8c%-8c%-8c%-8s%-8s\t%-8s\n",'A','B','C','D','E','F','G','H',
                'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',
                'Y','Z', "Marks", "Bytes", "Time [ms]");

        toWriteInFile = new StringBuffer(toPrint);

        //toWriteInFile.append("\r\t\t\t%-8c");
        stringToCount = pStringToCount;
        allPossiblePairs = new ArrayList<>();
        listOfTotals = new ArrayList<>();
        addAllPossiblePairs(listOfTotals);

        writer = new FileWriter(fileToWrite,true);
        if(fileToWrite.exists()){
            try {
                writer.write("");
            } catch (IOException ex) {
                System.out.print("\n" + ex.toString() + ": "
                        + Thread.currentThread().getName() + " failed");
            }
        }
        else{
            writer.write(toWriteInFile.toString());
        }
        closeWriter();
    }

    public synchronized void writeInFile() throws IOException{
        writer = new FileWriter(fileToWrite,true);
        if(fileToWrite.exists()){
            try {
                writer.write(toWriteInFile.toString());
            } catch (IOException ex) {
                System.out.print("\n" + ex.toString() + ": "
                        + Thread.currentThread().getName() + " failed");
            }
        }
        else{
            writer.write(toWriteInFile.toString());
        }
        closeWriter();
        toWriteInFile.delete(0, toWriteInFile.capacity());
    }

    public void closeWriter() throws IOException{
        writer.close();
    }

    public void printTotals(){
        System.out.print("\nTotal:\t\t");
        for(cPair pair : listOfTotals)
            System.out.printf("\t%s;%s",pair.getKey(),pair.getValue());
    }

    //Add pairs: A;0-B;0...Z;0 to an array
    public void addAllPossiblePairs(List<cPair> list){
        // allPossiblePairs = new ArrayList<>();
        list.add(new cPair("A",0));
        list.add(new cPair("B",0));
        list.add(new cPair("C",0));
        list.add(new cPair("D",0));
        list.add(new cPair("E",0));
        list.add(new cPair("F",0));
        list.add(new cPair("G",0));
        list.add(new cPair("H",0));
        list.add(new cPair("I",0));
        list.add(new cPair("J",0));
        list.add(new cPair("K",0));
        list.add(new cPair("L",0));
        list.add(new cPair("M",0));
        list.add(new cPair("N",0));
        list.add(new cPair("O",0));
        list.add(new cPair("P",0));
        list.add(new cPair("Q",0));
        list.add(new cPair("R",0));
        list.add(new cPair("S",0));
        list.add(new cPair("T",0));
        list.add(new cPair("U",0));
        list.add(new cPair("V",0));
        list.add(new cPair("W",0));
        list.add(new cPair("X",0));
        list.add(new cPair("Y",0));
        list.add(new cPair("Z",0));
        list.add(new cPair("Signs",0));
    }

    //Return the position for printing
    public int findPosition(String key){
        switch (key){
            case "A" : return 0;
            case "B" : return 1;
            case "C" : return 2;
            case "D" : return 3;
            case "E" : return 4;
            case "F" : return 5;
            case "G" : return 6;
            case "H" : return 7;
            case "I" : return 8;
            case "J" : return 9;
            case "K" : return 10;
            case "L" : return 11;
            case "M" : return 12;
            case "N" : return 13;
            case "O" : return 14;
            case "P" : return 15;
            case "Q" : return 16;
            case "R" : return 17;
            case "S" : return 18;
            case "T" : return 19;
            case "U" : return 20;
            case "V" : return 21;
            case "W" : return 22;
            case "X" : return 23;
            case "Y" : return 24;
            case "Z" : return 25;
            default : return 26;
        }
    }

    //Prints: Thread name: frequency, bytes, time
    public void printAllList() throws IOException{
        nameTask = Thread.currentThread().getName().toUpperCase();
        time = System.currentTimeMillis() - beginTime;

        if(time < newTime){
            time = newTime;
        }

        StringBuffer sum = new StringBuffer("");
        for(cPair pair : listOfTotals)
            sum.append(pair.getKey() + ";" + pair.getValue() + "\r\t");

        System.out.print("\n" + nameTask + ": " + allPossiblePairs
                + "\t\t" + (System.currentTimeMillis() - beginTime));
        toWriteInFile.append("\r\n").append(nameTask).append(": ")
                .append(allPossiblePairs).append("\t")
                .append(System.currentTimeMillis() - beginTime).append("\r\n")
                .append("\r\nTotal:\t\t").append(sum)
                .append("\tTotal time: " + time + "[ms]");

        sum.delete(0, toWriteInFile.capacity());
    }

    //Count the frequency of chars in each string ans returns a Map
    public Map<String,Long> countChars(String pStringToCount) throws UnsupportedEncodingException{
        try{
            Thread.sleep(generator.nextInt(1500)); //that the writer can write
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Thread  interrupted." + ex.toString());
        }

        frequency = new HashMap<>();
        stringToCount = pStringToCount;

        addAllPossiblePairs(allPossiblePairs);

        for(int i = 0; i < stringToCount.length(); i++) {
            if(null == frequency.get("" + stringToCount.charAt(i)))
                frequency.put("" + stringToCount.charAt(i),1L);
            else {
                Long valor = frequency.get("" + stringToCount.charAt(i));
                valor++;
                frequency.put("" + stringToCount.charAt(i),valor);
            }
        }

        /*To add to all Possible pairs*/
        Set<String> keys = frequency.keySet();
        Iterator<String> iterator = keys.iterator();
        String key;

        while(iterator.hasNext()) {
            key = iterator.next().trim(); //only chars. May be punctuation marks
            if(frequency.get(key) == null){continue;}

            cPair pair = new cPair(key, frequency.get(key));
            allPossiblePairs.set(findPosition(key), pair);
            listOfTotals.get(findPosition(key)).value += pair.getValue();
        }
        cPair pairOfBytes = new cPair("Bytes: ", pStringToCount.getBytes("UTF-8").length*2);
        allPossiblePairs.add(pairOfBytes);

        return frequency;
    }

    @Override //Calls to countChars and printAllList
    public void run() {
        try {
            beginTime = System.currentTimeMillis();
            countChars(stringToCount);
            printAllList();
            writeInFile();
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}

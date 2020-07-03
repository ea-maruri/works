package proyectoestruturadatosalgoritmos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;

/**
 * @author Alejandro Maruri
 * @author David Mena
 */

/*****
    *This class has all the the logic part to read the files (exceptions, main).
    *First read de file of exceptions and then the main file to make an inverted index.
    *We use an AVL to save the key-value: word-listOfWords in order to store alphabetically the words
    *There are many methods that allow know if words have special chars, like: (),¿?, etc.
        In the beginning an the end. Also, if a word is a number and make all words
        have the first letter capital. Ex: Word, Church, Computer.
*****/

public class cFileReader{
    private AVLTreeMap<String,cPairList> wordsAVL; //Reference of the AVL 
    private ArrayList<String> exceptionWords;
    
    cFileReader(){ //Constructor. Creates the AVL 
        wordsAVL = new AVLTreeMap<>();
    }
    
    //Reads the exception words txt by lines
    public void readExceptions(String file) throws IOException{
        String st; 
        FileReader fR = null; //FileReader: to read files.
        BufferedReader bR = null; //BufferedReader: read text of an entry of chars.
        exceptionWords = new ArrayList<>(); //List of exception words
        
        int counter = 0; //To add into positions of the list
        
        try {
            fR = new FileReader(file); //An instance of the file to read
            bR = new BufferedReader(fR); //
            
            while((st = bR.readLine()) != null){ //While has lines for reading
                exceptionWords.add(counter, st); //Add into the list
                counter++;
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }
        finally{ //Always close the file
            try {
                if (null != fR && null != bR){
                    fR.close();
                    bR.close();
                }
            }catch (IOException e2) {
                System.out.println(e2.toString());
           }
        }
    }
    
    //Method to read main file and adds entries to AVL
    public void readMainFile(String file) throws FileNotFoundException, IOException{
        String s1; //Each line of the file
        String s2; //Partitions or substrings
 
        //Charging the buffer with the content of the file
        BufferedReader br = new BufferedReader (new FileReader (file));
        
        int numLines = 0; //Indicates the current num of lines
        int numTokens = 0; //Indicates the num of words in the line
        int numPalabra = 0; //Indicate the number ow total words. Must be equals than size o AVL
        
        //Loop for all lines       
        while (br.ready()){ //While file has something to read
            s1 = br.readLine(); //Reading lint to line
            numLines++; //Each readLine increases numLines
            
            //Each line is split into substrings. The delimiter is the blank space. So we get word by word.
            StringTokenizer partitions = new StringTokenizer (s1);
            
            while (partitions.hasMoreTokens()){ //Loop for all the words (token) in the line
                s2 = partitions.nextToken(); //s2 is each word
                s2 = punctuationAtEnd(s2); //Remove characters at the end
                s2 = punctuationAtBeginning(s2); //Remove characters at the beginning
                s2 = isDigit(s2); //Remove numbers
                s2 = hasEnoughSize(s2); //If is no a word
                s2 = upperCaseFirstOne(s2); //Transform into a word
                s2 = s2.trim(); //Remov blanks. Just in case
                
                numTokens++; //Each word increases numTokens
                numPalabra++; //Number of total words
                               
                //Creating a cPair Object that is the position of the current token (word) s2
                cPair pair = new cPair(numLines,numTokens); 
                
                //In the case that AVL doesn't have the the word s2
                if(wordsAVL.get(s2) == null){
                    cPairList positions = new cPairList(); //A list of positions (cPair) is created
                    positions.addPair(pair); //We add the cPair 
                    wordsAVL.put(s2, positions); //Add to the AVL
                    //System.out.println("Inserting: " + s2);
                }    
                else{ //In the case that the word exists we only add the position in his own list.
                    //Only Modify the value, that in this case is a List
                    //System.out.println("Adding pair to: " + s2);
                    wordsAVL.get(s2).addPair(pair); //word exists and a pair is added to the existing list.
                }
            }
            numTokens = 0; //It resets and is the number of columns-words-tokens
        }
    }
    
    /*To write into a text file*/
    public void writeInFile(){
        FileWriter fW = null; //FileWriter: to read files.
        PrintWriter pW = null; //PrintWtiter: write in text file.
        StringTokenizer toTextFile = new StringTokenizer(this.toString(),"\n");
        
        try{
            fW = new FileWriter("C:\\Users\\Alejandro Maruri\\Desktop\\IndiceInvertido.txt");
            pW = new PrintWriter(fW);
            
            pW.println("\t\t\t\t\tIndice Invertido");
            while(toTextFile.hasMoreTokens()){
                pW.println(toTextFile.nextToken());
            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                if (null != fW && null != pW)
                    fW.close();
            }catch (IOException e2) {
                System.out.println(e2.toString());
           }
        }
    }
    
    /*To sear a group of words*/
    public void serchGroupOfWords(String groupOfWords){
        StringTokenizer tokens = new StringTokenizer(groupOfWords," ");
        StringBuilder sB = new StringBuilder("");
        
        while(tokens.hasMoreTokens()){
            sB.append(searchWord(tokens.nextToken())).append("\n");
        }
    }
    
    /*To search a specific word*/
    public String searchWord(String word){
        word = upperCaseFirstOne(word);
        if(wordsAVL.get(word) == null){
            System.out.println("Palabra no encontrada");
            return null;
        }
        else
            System.out.println("Palabra encontrada: \n" + word + " " + wordsAVL.get(word));
        return wordsAVL.get(word).toString();
    }
    
    /*Check the size of the string or if it is not null*/
    public String hasEnoughSize(String st){
        if(st.length() <= 2)
            st = "";
        return st;
    }
    
    /*Check If is digit and transforms it into an empty string*/
    public String isDigit(String st){
        if(st.contains("0") || st.contains("1") || st.contains("2")
                || st.contains("3") || st.contains("4") || st.contains("5")
                || st.contains("6") || st.contains("7") || st.contains("8")
                || st.contains("9") || st.contains("/")){
            st = "";
        }
        return st;
    }
    
    /*Check that it is a word without punctuation at the beginning*/
    public String punctuationAtBeginning(String st){
        if(st.contains("(") || st.contains(">") || st.contains("¡")
                || st.contains("¿") || st.contains("[") || st.contains("{")
                || st.contains("\"") || st.contains("-") || st.contains("/")
                || st.contains("*") || st.contains("-") || st.contains("<")
                || st.contains(".") || st.contains(",") || st.contains(";")
                || st.contains(":")){
            st = st.substring(1);
        }
        return st;
    }
    
    /*Check that it is a word without punctuation at the end*/
    public String punctuationAtEnd(String st){
        if(st.charAt(st.length()-1) == ',' || st.charAt(st.length()-1) == '.' 
                || st.charAt(st.length()-1) == ';' || st.charAt(st.length()-1) == '?'
                || st.charAt(st.length()-1) == '!' || st.charAt(st.length()-1) == ')'
                || st.charAt(st.length()-1) == ']' || st.charAt(st.length()-1) == '"'
                || st.charAt(st.length()-1) == '-' || st.charAt(st.length()-1) == '/'
                || st.charAt(st.length()-1) == '*' || st.charAt(st.length()-1) == ':'
                || st.charAt(st.length()-1) == '>'){
            st = st.substring(0,st.length()-1);
        }
        return st;
    }
    
    //Removes the all exception words in txt.
    public void removeUnnecessaryWords(){
        System.out.println("\nHay " + exceptionWords.size() + " palabras como excepcion");
        for(int i = 0; i < exceptionWords.size(); i++){
            System.out.println("Removing " + exceptionWords.get(i) + " en " + wordsAVL.get(exceptionWords.get(i)));
            wordsAVL.remove(exceptionWords.get(i));
        }
        wordsAVL.remove(""); //Remove blanks
    }
    
    //Making a format for strings. First letter is Capital
    public String upperCaseFirstOne(String st) {
        if (st == null || st.isEmpty()) 
            return st;            
        else
            return st.substring(0, 1).toUpperCase() + st.substring(1).toLowerCase(); 
    }
    
    @Override //Override toString. Return a String of wordsAVL
    public String toString(){
        StringBuffer sB = new StringBuffer("");
        int i = 0;
                
        for(Object o : wordsAVL.entrySet()){
            i++;
            sB.append(i).append(".- ").append(o.toString()).append("\n");
        }
        return sB.toString() + "\nSize of Inverted Index: " + wordsAVL.size();
    }
}
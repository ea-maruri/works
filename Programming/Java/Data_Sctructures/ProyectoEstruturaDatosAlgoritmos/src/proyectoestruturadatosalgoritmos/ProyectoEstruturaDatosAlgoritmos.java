package proyectoestruturadatosalgoritmos;

import java.io.IOException;
import java.util.Scanner;

/**
 * Alejandro Maruri
 * David Mena
 */

public class ProyectoEstruturaDatosAlgoritmos {
    public static void main(String[] args) throws IOException {
        Scanner entryWord = new Scanner(System.in);
        // Beginning Message 
        System.out.println("Programa que lee un archivo de texto (BIEN escrito),"
                + " lo separa palabra por palabra y registra su posicion.");
               
        cFileReader reader = new cFileReader(); //Instance of our class
        reader.readExceptions("C:\\Users\\Alejandro Maruri\\Desktop\\Exceptions.txt");
        reader.readMainFile("C:\\Users\\Alejandro Maruri\\Desktop\\ProyectFinal.txt");
        //reader.readMainFile("C:\\Users\\Alejandro Maruri\\Desktop\\NOTAS!!!.txt");
        
        reader.removeUnnecessaryWords();
        System.out.println("\n" + reader.toString());
        reader.writeInFile();
        
        System.out.print("\nPalabraa a buscar: ");
        String wordToSearch = entryWord.nextLine();
        
        reader.searchWord(wordToSearch);
        
        System.out.print("\nPalabras a buscar: ");
        String wordsToSearch = entryWord.nextLine();
        
        reader.serchGroupOfWords(wordsToSearch);
    }
}
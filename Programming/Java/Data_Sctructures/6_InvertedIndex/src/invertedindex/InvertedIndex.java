/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invertedindex;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Alejandro Maruri
 * @author David Mena
 */

public class InvertedIndex extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Inverted Index");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);
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
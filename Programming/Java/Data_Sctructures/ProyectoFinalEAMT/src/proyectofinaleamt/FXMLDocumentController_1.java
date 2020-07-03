package proyectofinaleamt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * @author Alejandro Maruri
 */

public class FXMLDocumentController_1 implements Initializable {
    cFileReader reader = new cFileReader(); //Instance of our class
    
    /*Labels*/
    @FXML private Label lbl_PushedButton;
    @FXML private Label lbl_AddException;
     
    /*TextFieldes*/
    @FXML private TextField txt_RouteExceptions;
    @FXML private TextField txt_RouteFile;
    @FXML private TextField txt_WordToSearch;
    @FXML private TextField txt_FoundedWord;
    @FXML private TextField txt_GroupToSearch;
    @FXML private TextField txt_AddException;
    
    /*Text Area*/
    @FXML private TextArea txt_FoundedGroup;
    
    /*On Action*/
    @FXML private void GenerateIndex(ActionEvent event) throws IOException{
       // reader.emptyInvertedIndexFile();
        lbl_AddException.setText("");
        lbl_PushedButton.setText("Generar índice");
        reader.readExceptions(txt_RouteExceptions.getText().trim()); //C:\\Users\\Alejandro Maruri\\Desktop\\Exceptions.txt
        reader.readMainFile(txt_RouteFile.getText().trim()); //C:\\Users\\Alejandro Maruri\\Desktop\\ProyectFinal.txt
        reader.removeUnnecessaryWords();
        System.out.println("\n" + reader.toString());
        reader.writeInFile();
        reader.OpenFile("C:\\Users\\Alejandro Maruri\\Desktop\\IndiceInvertido.txt");
    }
    
    @FXML private void SearchWord(ActionEvent event){
        lbl_AddException.setText("");
        lbl_PushedButton.setText("Buscar palabra");
        String foundedWord = reader.searchWord(txt_WordToSearch.getText().trim());
        txt_FoundedWord.setText(foundedWord);
        txt_WordToSearch.setText("");
    }
    
    @FXML private void SearchGroup(ActionEvent event){
        lbl_AddException.setText("");
        lbl_PushedButton.setText("Buscar grupo de palabras");
        String foundedGroup = reader.serchGroupOfWords(txt_GroupToSearch.getText().trim());
        txt_FoundedGroup.setText(foundedGroup);
    }
    
    @FXML private void AddException(ActionEvent event) throws IOException{
        lbl_PushedButton.setText("Añadiendo a excepciones");
        reader.writeInExceptions(txt_RouteExceptions.getText().trim(), txt_AddException.getText().trim());
        reader.readExceptions(txt_RouteExceptions.getText().trim());
        reader.removeUnnecessaryWords();
        reader.OpenFile(txt_RouteExceptions.getText().trim());
        lbl_AddException.setText("Se añadió excepción");
        txt_AddException.setText("");
    }
            
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            lbl_PushedButton.setText("");
            lbl_AddException.setText("");
            txt_FoundedWord.setEditable(false);
            txt_FoundedGroup.setEditable(false);
            
            //Predeterminated texts
            txt_RouteExceptions.setText("C:\\\\Users\\\\Alejandro Maruri\\\\Desktop\\\\Exceptions.txt");
            txt_RouteFile.setText("C:\\\\Users\\\\Alejandro Maruri\\\\Desktop\\\\ProyectFinal.txt");
    }     
}
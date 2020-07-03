import java.io.BufferedReader;
import java.io.FileReader;


/**
 *
 * @author EAMT
 */
public class File {

    public File(){}

    public File(String path){
        java.io.File = new FileReader()
    }


    public String readFile(String path){
        StringBuilder sb = new StringBuilder();

        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String bfRead;

            // While there is data
            while((bfRead = bf.readLine()) != null){
                sb.append(bfRead); // Concatenate texts
            }

        }catch(Exception e){
            System.out.println("File not Found: " + e);
        }

        return sb.toString();
    }
}

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EAMT
 * Read the text and make an only String.
 */

public class cReadText {

    // private ArrayListarrayOfSubStrings = new
    private StringBuffer stringOfFile;
    private FileReader fR = null; //FileReader. To read files.
    private BufferedReader bR = null; //BufferedReader: read text of an entry of chars

    /**
     * Returns the size of the file on bytes
     * @param file. File that will be readed
     * @return The size of file
     * @throws java.io.IOException . May be there is no the file
     */
    public long sizeOfFile(String file) throws IOException {
        Path path = Paths.get(file);
        return File.size(path);
    }

    /**
     * Makes subString from the string of *
     * @param fileString. The substrings of file (that is made an only string).
     * @param pieceSize. The size to slice string
     * @param code. Use code that recognize the alphabet
     * @return A list of strings with all substrings of the string from file
     * @throws java.io.UnsupportedEncodingException . May be there is no the type of code
     */
    public List<String> makeSubStrings(String fileString, int pieceSize, String code) throws UnsupportedEncodingException {
        List<String> listOfPieces = new ArrayList<>();
        final int end = fileString.length();
        int from = 0, to = 0;

        do {
            if(to + pieceSize > end)
                to = end;
            else
                to = to + pieceSize;

            String piece = fileString.substring(from, to); // get piece

            listOfPieces.add(piece); // add piece to collection
            from = to; // next piece
        } while (to < end);

        return listOfPieces;
    }

    /**
     * @param pFile. The file to make a string
     * @return Makes an only string of file.
     * @throws java.io.IOException
     */
    public String readTextToString(String pFile) throws IOException{
        String st; //Each line of txt
        stringOfFile = new StringBuffer();

        try {
            fR = new FileReader(pFile); //An instance of the file to read
            bR = new BufferedReader(fR); //

            while((st = bR.readLine()) != null) //While has lines for reading
                stringOfFile = stringOfFile.append(st);

        } catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }finally{ //Always close the file
            try {
                if (null != fR && null != bR){
                    fR.close();
                    bR.close();
                }
            }catch (IOException e2) {
                System.out.println(e2.toString());
            }
        }
        return stringOfFile.toString().toUpperCase();
    }
}

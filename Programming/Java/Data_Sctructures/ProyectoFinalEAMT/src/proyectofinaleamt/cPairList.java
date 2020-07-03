package proyectofinaleamt;

/**
 * @author Alejandro Maruri
 */

public class cPairList{
    private ArrayList<cPair> ListOfPairs; //= new ArrayList<>();
    private int mySize = 0;
    
    //Constructor
    public cPairList(){ListOfPairs = new ArrayList<>();}; 
    
    //Getters and Setters
    public void addPair(cPair pair){ListOfPairs.add(mySize++, pair);}
        
    public cPair getPairAt(int i){return ListOfPairs.get(i);}
        
   @Override //Override toString. Print all Pairs
    public String toString(){
        StringBuffer st = new StringBuffer("");
        
        for(int i = 0; i < ListOfPairs.size(); i++)
            st.append(getPairAt(i));
        
        return st.toString();   
    }
}
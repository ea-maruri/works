package deber5_randomcharkeys;

/**
 * @author Alejandro Maruri
 */

public final class cRandomCharKey { //Final cause NO inheritance
    
    String[] Table; //"Table" is an Array
    private long N; //Number of keys
    private long size; //Spaces used
    private long loadFactor; //N + factor*N
    private long capacity; //Real number of spaces in Array
    private int collisions, pasos, mayorPasos; //Number of colisions
    private final char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l',
        'm','n','o','p','q','r','s','t','u','v','w','x','y','z'}; //Array of letters
    private StringBuilder key = new StringBuilder(""); //key made of letters
    
    /*Default Constructor*/
    public cRandomCharKey(){
        setN(1); //Default size 1M.
        Table = new String[(int)getN()]; //N + 0.6N, size + load factor
        setSize(0);
        setLoadFactor(getN()*3/5); //N*0.6
        setCapacity(getN()); //At the begining capacity equals N
        collisions = 0;
        mayorPasos = 0;
        
        System.out.println(toString());
        
        for(int i = 0; i < N; i++){
            String keyS = createKey(); //Create a key
            if(loadFactor == size){ //Resize, cause load factor
                System.out.println("Se superó factor de carga en la inserción" +
                    " número " + getSize() + " Se redimensiona la Tabla...");
                Table = resizeTable(Table);
                
                System.out.println("\nNow, Table" +
               "\n\tNumber of keys:" + getN() + "\n" +
               "\tSpace used: " + getSize() + "\n" +
               "\tLoad factor: " + getLoadFactor() + "\n" +
               "\tCapacity: " + getCapacity() + "\n");
                
                /*for (String Table1 : Table) 
                    System.out.println(Table1 + ", ");*/
            }
            else
                /*System.out.println("No se supera factor de carga, hay " + getSize() 
                + " de " + getCapacity() + " Con factor de carga: " + getLoadFactor());*/
            
            if(Table[position(keyS)] == null){ //If this position null, insert
                Table[position(keyS)] = keyS; //Adding key in table
                /*System.out.println((i+1) + ".- Se insertó \"" + Table[position(keyS)]
                        + "\" en: " + position(keyS) + " de " + (getCapacity() - 1));*/
                size++;
            }
            else{ //If in there is no space
                /*System.out.println((i+1) + ".- NO se insertó \"" + keyS
                    + "\" en: " + position(keyS) + " de " + (getCapacity() - 1) 
                    + " por colision, estaba: " + Table[position(keyS)]);*/
                searchForPlace(Table, position(keyS), keyS);
                collisions++;
            }   
        }
        System.out.println("\nInformación:\n\t" + collisions + " colisiones totales");
    }
    
    /*Contructor with parameter of size*/
    public cRandomCharKey(int n){ //int between 1 to 10
        setN(n); //size n*M.
        Table = new String[(int)getN()]; //N + 0.6N, size + load factor
        setSize(0);
        setLoadFactor(getN()*3/5); //N*0.6
        setCapacity(getN()); //At the begining capacity equals N
        collisions = 0;
        mayorPasos = 0;
        
        System.out.println(toString());
        
        for(int i = 0; i < N; i++){
            String keyS = createKey(); //Create a key
            if(loadFactor == size){ //Resize, cause load factor
                System.out.println("Se superó factor de carga en la inserción" +
                    " número " + getSize() + " Se redimensiona la Tabla...");
                Table = resizeTable(Table);
                
                System.out.println("\nNow, Table" +
               "\n\tNumber of keys:" + getN() + "\n" +
               "\tSpace used: " + getSize() + "\n" +
               "\tLoad factor: " + getLoadFactor() + "\n" +
               "\tCapacity: " + getCapacity() + "\n");
                
                /*for (String Table1 : Table) 
                    System.out.println(Table1 + ", ");*/
            }
            else
                /*System.out.println("No se supera factor de carga, hay " + getSize() 
                + " de " + getCapacity() + " Con factor de carga: " + getLoadFactor());*/
            
            if(Table[position(keyS)] == null){ //If this position null, insert
                Table[position(keyS)] = keyS; //Adding key in table
                /*System.out.println((i+1) + ".- Se insertó \"" + Table[position(keyS)]
                        + "\" en: " + position(keyS) + " de " + (getCapacity() - 1));*/
                size++;
            }
            else{ //If in there is no space
                /*System.out.println((i+1) + ".- NO se insertó \"" + keyS
                    + "\" en: " + position(keyS) + " de " + (getCapacity() - 1) 
                    + " por colision, estaba: " + Table[position(keyS)]);*/
                searchForPlace(Table, position(keyS), keyS);
                collisions++;
                if(pasos > mayorPasos)
                    mayorPasos = pasos;
            }   
        }
        System.out.println("\nInformación:\n\t" + collisions + " colisiones totales"
            + "\n\t" + mayorPasos + " máximo de pasos para encontrar posición");
    }
    
    /*Get & Set*/
    //Set. 
    public void setN(long n){N = n*1000000;} //Tronsforming to Million //int between 1 to 10. N Millon
    public void setSize(long pSize){size = pSize;}
    public void setLoadFactor(long pLoadFactor){loadFactor = pLoadFactor;}
    public void setCapacity(long pCapacity){capacity = pCapacity;}
    //Get
    public long getN(){return N;} //Return N in Millon.
    public long getSize(){return size;}
    public long getLoadFactor(){return loadFactor;}
    public long getCapacity(){return capacity;}
    public int colisions(){return collisions;}
       
    /*Method that create a key with the letters*/
    public String createKey(){
        /*Creating keys*/
        for(int i = 0; i < 10; i++){
            int rand = (int)(Math.random()*26);
            key.append(letters[rand]);
        }
        String keyReturn = key.toString();
        key.delete(0, key.length()); //cut key (StringBuilder
        return keyReturn; // The new key
    }
    
    //Hash for each string (each char, each key)
    public int HashCodePerKey(String pKey){ 
        //int h = 
        int h = pKey.codePointAt(0); //ASCII of first char
        for (int i = 1; i < key.length(); i++){
            System.out.println(key.charAt(i) + " = " + key.codePointAt(i));
            h += pKey.codePointAt(i)*(7^i); //Horner
        }
        return h;
    }
    
    //Calculating the position of key in Table
    public int position(String pKey){
        return (int)((HashCodePerKey(pKey) + 11)%getCapacity());
    }
    
    //Searching for a place if there is a collision
    public void searchForPlace(String[] T, int pos, String pKey){
        pasos = 0;
        for(int i = pos; i < T.length; i++){
            if(T[i] == null){
               T[i] = pKey;
               /*System.out.println("\tSearching..." + " se insertó \"" + T[i]
                    + "\" en: " + i + " de " + getCapacity() + " dio " + pasos + " pasos.");*/
               size++;
               break;
            }
            if(i == T.length){
                i = 0;
                System.out.println("Se llegó al límite");
            }
            if(i == (pos - 1)){ //If went around all of table 
                System.out.println("No se pudo insertar en ninguna posición");
                break;
            }
            pasos++; //Count of pasos
        }    
    }
    
    //Resize if size equals to loadFactor (size is doubled)
    public String[] resizeTable(String[] pTable){
        String[] newTable = new String[(int)getN()*2]; //Double the size
        System.arraycopy(pTable, 0, newTable, 0, Table.length); //Copying array
        
        setCapacity(newTable.length); //At the begining capacity equals N
        setLoadFactor(getCapacity()*3/5); //N*0.6
        return newTable;
    }
    
    @Override //ToString. Info
    public String toString(){
        return "You have created a Hash-Table:" +
               "\n\tNumber of keys:" + getN() + "\n" +
               "\tLoad factor: " + getLoadFactor() + "\n" +
               "\tCapacity: " + getCapacity() + "\n";
    }
}
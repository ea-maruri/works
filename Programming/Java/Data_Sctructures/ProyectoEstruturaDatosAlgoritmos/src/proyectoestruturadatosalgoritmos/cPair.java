package proyectoestruturadatosalgoritmos;

/**
 * @author Alejandro Maruri
 * @author David Mena
 */

public class cPair {
    private int row;
    private int column;
    
    /*Constructors*/
    //Default
    public cPair(){this(0,0);} 
    //Copy Constructor
    public cPair(cPair pPair){ 
        this(pPair.row,pPair.column);   
    }
    //Constructor row, column
    public cPair(int pRow, int pColumn){ 
        setRow(pRow);
        setColumn(pColumn);
    }
    
    /*Getters and Setters*/
    //Using involving classes
    public Integer getRow() {return row;} 
    public Integer getColumn() {return column;}
    public void setRow(int row) {this.row = row;}
    public void setColumn(int column) {this.column = column;}   
    
    @Override //Override toString. Print: "(x,y)"
    public String toString(){
        return "(" + getRow() + "," + getColumn() + ") ";//String.format("");    
    }
    
   @Override //Override equals
    public boolean equals(Object pPair){
        if(pPair instanceof cPair){ // All atributes are equal
            return this.row == ((cPair)pPair).row && this.column == ((cPair)pPair).column; 
        }
        else 
            return false;
    }

    @Override //Override Has Code
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.row;
        hash = 79 * hash + this.column;
        return hash;
    }
    
    public int compareTo(cPair pPair) { // Returns negative, positive or zero
        int r;
        r = this.getRow().compareTo(pPair.getRow()); // First: compare the row
        if(r == 0){ // If are equals
            r = this.getColumn().compareTo(pPair.getColumn()); // Then, column
        }
        return r;    }
}
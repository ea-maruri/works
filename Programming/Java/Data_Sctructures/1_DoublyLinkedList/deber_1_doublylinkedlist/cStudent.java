package deber_1_doublylinkedlist;

import java.util.Objects;

public class cStudent { // class Student
    
    //Atributes
    private String name;
    private int age;
    private int code;
    private String semester;
    private int credits;

    cStudent(){} // Constructor without parameters
    //Constructor with 4 parameters
    cStudent(String pName, int pAge, int pCode, String pSemester, int pCredits){ 
        setName(pName);
        setAge(pAge);
        setCode(pCode);
        setSemester(pSemester);
        setCredits(pCredits);
    }
    
    //Get methods
    public String getName() {return name;}
    public int getAge() {return age;}
    public int getCode() {return code;}
    public String getSemester() {return semester;}
    public int getCredits(){return credits;}
    
    //Set methods
    public void setName(String pName) {name = pName;}
    public void setAge(int pAge) {age = pAge;}
    public void setCode(int pCode) {code = pCode;}
    public void setSemester(String pSemester) {semester = pSemester;}
    public void setCredits(int pCredits) {credits = pCredits;}
    
    @Override //Override toString
    public String toString(){return String.format("\tName: %s%n\tAge: %d%n\tCode: "
        + "%d%n\tSemester: %s%n\tCredits: %d%n", getName(), getAge(), getCode(), getSemester(), getCredits());}
    
    @Override //Override equals. Gotten from cCirculo (class created in POO)
    public boolean equals(Object student){
        if(student instanceof cStudent){ // All atributes are equal
            return this.name == ((cStudent)student).name && this.age == ((cStudent)student).age && this.code == ((cStudent)student).code && this.semester == ((cStudent)student).semester && this.credits == ((cStudent)student).credits;
        }
        else 
            return false;
    }

    @Override // Hashcode created by Netbeans IDE
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + this.age;
        hash = 19 * hash + this.code;
        hash = 19 * hash + Objects.hashCode(this.semester);
        return hash;
    }
}
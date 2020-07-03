package deber6_avltree;

import java.util.Objects;

public class cStudent implements iStudent{ // class Student
    //Atributes
    private String name;
    private String lastName;
    private int age;
    private int code;
    private String semester;
    private int credits;

    // Constructor without parameters
    cStudent(){this("","",0,0,"",0);} 
    
    // Copy Contructor
    cStudent(final cStudent estud){
        name = estud.name;
        lastName = estud.lastName;
        age = estud.age;
        code = estud.code;
        semester = estud.semester;
        credits = estud.credits;
    } 
    
    //Constructor with 4 parameters
    cStudent(String pName, String pLName, int pAge, int pCode, String pSemester, int pCredits){ 
        setName(pName);
        setLastName(pLName);
        setAge(pAge);
        setCode(pCode);
        setSemester(pSemester);
        setCredits(pCredits);
    }
    
    //Get methods
    @Override
    public String getName() {return name;}
    @Override
    public String getLastName(){return lastName;}
    @Override
    public Integer getAge() {return age;}
    @Override
    public Integer getCode() {return code;}
    @Override
    public String getSemester() {return semester;}
    @Override
    public Integer getCredits(){return credits;}
    
    //Set methods
    @Override
    public void setName(String pName) {name = pName;}
    @Override
    public void setLastName(String pLName){lastName = pLName;}
    @Override
    public void setAge(int pAge) {age = pAge;}
    @Override
    public void setCode(int pCode) {code = pCode;}
    @Override
    public void setSemester(String pSemester) {semester = pSemester;}
    @Override
    public void setCredits(int pCredits) {credits = pCredits;}
    
    @Override //Override toString
    public String toString(){return String.format("\tName: %s%n\tLastName: %s%n"
            + "\tAge: %d%n\tCode: %d%n\tSemester: %s%n\tCredits: %d%n", 
            getName(), getLastName(), getAge(), getCode(), getSemester(), getCredits());
    }
    
    @Override //Override equals. Gotten from cCirculo (class created in POO)
    public boolean equals(Object student){
        if(student instanceof cStudent){ // All atributes are equal
            return this.name.equals(((cStudent)student).name) 
                    && this.lastName.equals(((cStudent)student).lastName)
                    && this.age == ((cStudent)student).age 
                    && this.code == ((cStudent)student).code 
                    && this.semester.equals(((cStudent)student).semester) 
                    && this.credits == ((cStudent)student).credits;
        }
        else 
            return false;
    }

    @Override // Hashcode created by Netbeans IDE
    public int hashCode() {
        int hash = 7;
        hash += 19 * hash + Objects.hashCode(this.name);
        hash += 19 * hash + Objects.hash(this.lastName);
        hash += 19 * hash + this.age;
        hash += 19 * hash + this.code;
        hash += 19 * hash + Objects.hashCode(this.semester);
        hash += 19 * hash + this.credits;
        return hash;
    }
    
    @Override //Compare...
    public int compareTo(iStudent student) { // Return negative, positive or zero
        int r;
        r = this.getLastName().compareTo(student.getLastName()); // First last name
        if(r == 0){ // If are equals
            r = this.getName().compareTo(student.getName()); // Then name
            if(r == 0){
                r = this.getCode().compareTo(student.getCode()); // Code
                if(r == 0){
                    r = this.getSemester().compareTo(student.getSemester()); // Semester
                    if(r == 0){
                        r = this.getCredits().compareTo(student.getCredits()); // Credits
                        if(r == 0){
                            r = this.getAge().compareTo(student.getAge());
                        }
                    }
                }
            }
        }
        return r;//To change body of generated methods, choose Tools | Templates.
    }
}
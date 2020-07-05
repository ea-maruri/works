import java.util.Objects;


/**
 * @author AM-GIK
 *
 * Class that represents a simple student
 * */

public class cStudent { // class Student

    // Attributes
    private String name;
    private int age;
    private int code;
    private String semester;
    private int credits;

    // Constructor without parameters
    public cStudent(){} 

    //Constructor with 4 parameters
    public cStudent(String pName, int pAge, int pCode, String pSemester, int pCredits){
        setName(pName);
        setAge(pAge);
        setCode(pCode);
        setSemester(pSemester);
        setCredits(pCredits);
    }

    //Get methods
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getSemester() {
        return this.semester;
    }
    
    public int getCredits(){
        return this.credits;
    }

    //Set methods
    public void setName(String pName) {
        if(pName.length() > 5)
            this.name = pName;
        else{
            this.name = "BadName";
        }
    }

    public void setAge(int pAge) {
        if(pAge >= 0 && pAge <= 110) {
            this.age = pAge;
        }
        else {
            this.age = 0;
        }
    }

    public void setCode(int pCode) {
        this.code = Math.max(pCode, 0); // Zero or the given number
    }

    // Check this set method
    public void setSemester(String pSemester) {
        this.semester = pSemester;
    }

    public void setCredits(int pCredits) {
        if(pCredits > 0){
            this.credits = pCredits;
        }
        else {
            this.credits = -1;
        }
    }


    @Override //Override toString
    public String toString(){
        return String.format(
                "\tName: %s%n" +
                "\tAge: %d%n" +
                "\tCode: %d%n" +
                "\tSemester: %s%n" +
                "\tCredits: %d%n",
                getName(), getAge(), getCode(), getSemester(), getCredits());
    }


    @Override //Override equals.
    public boolean equals(Object student){
        if(student instanceof cStudent){
            // All attributes are equal
            return this.name.equals(((cStudent) student).name) && this.age == ((cStudent)student).age
                    && this.code == ((cStudent)student).code && this.semester.equals(((cStudent) student).semester)
                    && this.credits == ((cStudent)student).credits;
        }
        else {
            return false;
        }
    }

    @Override // Override Hashcode
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + this.age;
        hash = 19 * hash + this.code;
        hash = 19 * hash + Objects.hashCode(this.semester);
        return hash;
    }
}
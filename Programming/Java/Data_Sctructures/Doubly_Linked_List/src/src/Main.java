
/**
 * @author AM-GIK
 * 24/08/2018
 */

public class Main {

    public static void main(String[] args) {
        DoublyLinkedList<cStudent> studList = new DoublyLinkedList<>();
        String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack", "Anna", "Paul", "Bob"};
        int[] ages = {21, 20, 25, 19, 22, 20, 24, 22};
        int[] codes = {111, 222, 333, 444, 555, 666, 777, 888};
        String[] semesters = {"First", "Fourth", "Tenth", "Fifth", "Eigth", "Sixth", "Seventh", "Second"};
        int[] credits = {23, 85, 175, 92, 120, 100, 105, 42};

        if(studList.isEmpty())
            System.out.println("In the beginning studList is Empty\n");

        for (int i = 0; i < names.length/2; i++) {
            cStudent stud = new cStudent(names[i], ages[i], codes[i], semesters[i], credits[i]);
            System.out.println("Adding at the end:\n" + stud);
            studList.addLast(stud); //Adding at the end
        }
        for (int i = names.length/2; i < names.length; i++) {
            cStudent stud = new cStudent(names[i], ages[i], codes[i], semesters[i], credits[i]);
            System.out.println("Adding at the beginning:\n" + stud);
            studList.addFirst(stud); //Adding at the beginning
        }

        System.out.println("Size of studList is: " + Integer.toString(studList.size()) + "\n");//Proving size

        System.out.println("First in studList: \n" + studList.first()); //Showing first
        System.out.println("Removing first in studList...\n");
        studList.removeFirst(); //Remove first
        System.out.println("New first in studList: \n" + studList.first()); //Show New first

        System.out.println("Last in studList: \n" + studList.last()); //Show last
        System.out.println("Removing last in studList...\n");
        studList.removeLast(); //Remove last
        System.out.println("New last in studList: \n" + studList.last()); //Show new last

        cStudent stud = new cStudent("New", 15, 999, "Tenth", 200);
        cStudent stud1 = new cStudent("New", 15, 999, "Tenth", 200);
        cStudent stud2 = new cStudent("New1", 15, 999, "Tenth", 200);

        System.out.println("toString method: \n" + stud.toString());

        //Proving equals of cStudent
        if(stud.equals(stud1))
            System.out.println("stud is equal to stud1\n");

        if(stud.equals(stud2))
            System.out.println("stud is not equal to stud2\n");

        if(!studList.isEmpty())
            System.out.println("Size of studList: " + Integer.toString(studList.size()) + "\n"); //The last size

        //New list for camparing with the last one
        DoublyLinkedList<cStudent> studList1 = new DoublyLinkedList<>();

        for (int i = 0; i < names.length/2; i++) {
            cStudent stud3 = new cStudent(names[i], ages[i], codes[i], semesters[i], credits[i]);
            System.out.println("Adding at the end (New List):\n" + stud);
            studList1.addLast(stud); //Adding at the end
        }
        // Provin eguals ofDoublyLinkedList
        if(studList.equals(studList))
            System.out.println("It is comparing the same list. Obviously, is equal\n");

        if(!studList1.equals(studList))
            System.out.println("studList1 and studList are not equal\n"); //Do not have the same size (in this case)

        System.out.println("Hash code of a cStudent: " + Integer.toString(stud.hashCode()) + "\n");
        System.out.println("Hschcode of a DoublyLinkedList: "  + Integer.toString(studList.hashCode()) + "\n");

        //toString method
        // System.out.println(studList.toString());
    }
}

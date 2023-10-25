package maman14_1;

import java.util.Iterator;

public class AssociationTableMain {

    public static void main(String[] args) {
        Student[] students = {
                new Student("John", "Doe", 122, 1990),
                new Student("Jane", "Doe", 111, 1992),
                new Student("Alex", "Mayer", 254, 1980)
        };
        AssociationTable<Student, Integer> studentToGrade = new AssociationTable<>(students, new Integer[]{100, 90, 80});
        Student s = new Student("Paul", "Block", 121, 1995);
        studentToGrade.add(s, 70); // Add new student
        studentToGrade.add(s, 75); // Change grade
        studentToGrade.remove(students[0]); // Remove student

        System.out.println("There are " + studentToGrade.size() + " students:\n");
        Iterator<Student> iter = studentToGrade.keyIterator();
        while (iter.hasNext()) {
            Student student = iter.next();
            System.out.println(student);
            System.out.println("Grade - " + studentToGrade.get(student) + "\n");
        }
    }
}

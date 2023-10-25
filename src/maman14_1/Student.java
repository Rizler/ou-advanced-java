package maman14_1;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private int id;
    private int birthYear;

    public Student(String firstName, String lastName, int id, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", id=" + id +
                ", birthYear=" + birthYear +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (this.id < o.id) {
            return -1;
        } else if (this.id > o.id) {
            return 1;
        }
        return 0;
    }
}

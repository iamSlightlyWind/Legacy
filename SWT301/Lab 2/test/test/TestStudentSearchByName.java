package test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;
import student.Student;
import java.util.ArrayList;

public class TestStudentSearchByName {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void searchStudentsByName() {
        ArrayList<Student> foundStudents = sm.searchStudentsByName("John", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("John Doe")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Alex Johnson")));
    }

    @Test
    public void searchStudentsByWrongName() {
        ArrayList<Student> foundStudents = sm.searchStudentsByName("Doe", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("John Doe")));
        assertFalse(foundStudents.stream().anyMatch(student -> student.getName().equals("Alex Johnson")));
    }

    @Test
    public void searchStudentsUnicode() {
        ArrayList<Student> foundStudents = sm.searchStudentsByName("Văn", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Nguyễn Văn A")));
    }

    @Test
    public void searchAllStudents(){
        ArrayList<Student> foundStudents = sm.searchStudentsByName("", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("John Doe")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Alex Johnson")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Jane Smith")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Nguyễn Văn A")));
    }
}
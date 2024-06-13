package test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;
import student.Student;
import java.util.ArrayList;

public class TestStudentSort {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void sortStudentGetFirstN() {
        ArrayList<Student> sortedStudents = sm.sortStudentsByName();
        assertEquals("Adam Damn", sortedStudents.get(0).getName());
    }

    @Test
    public void sortStudentsGetLast() {
        ArrayList<Student> sortedStudents = sm.sortStudentsByName();
        assertEquals("Social Dude", sortedStudents.get(sortedStudents.size() - 1).getName());
    }

    @Test
    public void nullInput() {
        StudentManager sm = new StudentManager();
        assertThrows(IllegalArgumentException.class, () -> sm.searchStudentsByName("John", null));
    }

    @Test
    public void sortStudentsByNameEmpty() {
        StudentManager sm = new StudentManager();
        ArrayList<Student> sortedStudents = sm.sortStudentsByName();
        assertTrue(sortedStudents.isEmpty());
    }

    @Test
    public void sortStudentsByNameAlreadySorted() {
        StudentManager sm = new StudentManager();
        sm.student.add(new Student(1, "Adam Damn"));
        sm.student.add(new Student(2, "Social Dude"));
        ArrayList<Student> sortedStudents = sm.sortStudentsByName();
        assertEquals("Adam Damn", sortedStudents.get(0).getName());
        assertEquals("Social Dude", sortedStudents.get(1).getName());
    }

    @Test
    public void sortStudentsByNameReverseOrder() {
        StudentManager sm = new StudentManager();
        sm.student.add(new Student(1, "Adam Damn"));
        sm.student.add(new Student(2, "Social Dude"));
        ArrayList<Student> sortedStudents = sm.sortStudentsByName();
        assertEquals("Adam Damn", sortedStudents.get(0).getName());
        assertEquals("Social Dude", sortedStudents.get(1).getName());
    }
}
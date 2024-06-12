package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import manager.StudentManager;
import student.Student;

public class TestStudentSort {
    @Test
    public void testSortStudentsByNameFirst() {
        StudentManager sm = new StudentManager();
        sm.populate();

        ArrayList<Student> sortedStudents = sm.sortStudentsByName();

        assertEquals("Adam Damn", sortedStudents.get(0).getName());
    }

    @Test
    public void testSortStudentsByNameLast() {
        StudentManager sm = new StudentManager();
        sm.populate();

        ArrayList<Student> sortedStudents = sm.sortStudentsByName();

        assertEquals("Social Dude", sortedStudents.get(sortedStudents.size() - 1).getName());
    }
}

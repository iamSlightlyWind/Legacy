package test;

import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;
import student.Student;
import java.util.ArrayList;

public class TestStudentSearch {
    @Test
    public void testSearchStudentsByName() {
        StudentManager sm = new StudentManager();
        sm.populate();

        ArrayList<Student> foundStudents = sm.searchStudentsByName("John", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("John Doe")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Alex Johnson")));
    }
}

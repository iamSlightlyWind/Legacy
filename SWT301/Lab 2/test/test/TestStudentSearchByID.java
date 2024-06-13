package test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import manager.StudentManager;

public class TestStudentSearchByID {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void foundStudent() {
        String studentName = sm.findStudent(17);
        assertEquals("Tìm thấy sinh viên: Jane Smith", studentName);
    }

    @Test
    public void notFoundStudent() {
        String studentName = sm.findStudent(999);
        assertEquals("", studentName);
    }

    @Test
    public void negativeId() {
        String studentName = sm.findStudent(-1);
        assertEquals("", studentName);
    }

    @Test
    public void searchByID() {
        String input = "17\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(17, sm.studentSearchByID());
    }

    @Test
    public void searchByIDString() {
        String input = "asdf\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(InputMismatchException.class, () -> sm.studentSearchByID());
    }
}
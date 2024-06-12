package test;

import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;

public class TestStudentFind {
    @Test
    public void testFoundStudent() {
        StudentManager sm = new StudentManager();
        sm.populate();

        String studentName = sm.findStudent(17);
        assertEquals("Tìm thấy sinh viên: Jane Smith", studentName);

        studentName = sm.findStudent(999);
        assertEquals("", studentName);
    }
}

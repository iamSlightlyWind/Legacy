package test;

import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;

public class TestSubjectAdd {
    @Test
    public void testSubjectAdd1() {
        StudentManager sm = new StudentManager();
        sm.populate();

        assertEquals(1, sm.student.get(0).addSubject(2, 2));
        assertEquals(-1, sm.student.get(0).addSubject(5, 2));
    }

    @Test
    public void testSemesterAdd2() {
        StudentManager sm = new StudentManager();
        sm.populate();

        assertEquals(-1, sm.student.get(0).addSubject(2, -3));
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
    }

    @Test
    public void testDuplicateAdd3() {
        StudentManager sm = new StudentManager();
        sm.populate();
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
        assertEquals(-1, sm.student.get(0).addSubject(2, 2));

    }
}

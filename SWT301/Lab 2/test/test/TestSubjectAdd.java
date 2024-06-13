package test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;

public class TestSubjectAdd {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void subjectAdd1() {
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
        assertEquals(-1, sm.student.get(0).addSubject(5, 2));
    }

    @Test
    public void semesterAdd2() {
        assertEquals(-1, sm.student.get(0).addSubject(2, -3));
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
    }

    @Test
    public void duplicateAdd3() {
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
        assertEquals(-1, sm.student.get(0).addSubject(2, 2));
    }
}
package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;

public class TestCountStudent {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void existingSubject() {
        int count = sm.countStudent(1);
        assertTrue(count > 0);
    }

    @Test
    public void nonExistingSubject() {
        int count = sm.countStudent(999);
        assertEquals(0, count);
    }

    @Test
    public void negativeSubjectId() {
        int count = sm.countStudent(-1);
        assertEquals(0, count);
    }

    @Test
    public void zeroSubjectId() {
        int count = sm.countStudent(0);
        assertEquals(0, count);
    }

    @Test
    public void multipleStudentsLearning() {
        int count = sm.countStudent(2);
        assertTrue(count > 1);
    }
}
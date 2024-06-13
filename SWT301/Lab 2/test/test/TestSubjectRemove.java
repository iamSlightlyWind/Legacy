package test;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;
import student.Student;

public class TestSubjectRemove {
    private StudentManager sm;

    @Before
    public void setUp() {
        sm = new StudentManager();
        sm.populate();
    }

    @Test
    public void invalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> sm.student.get(0).removeSubject(99));
    }

    @Test
    public void negativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> sm.student.get(0).removeSubject(-2));
    }

    @Test
    public void validIndex() {
        assertEquals(1, sm.student.get(0).removeSubject(1));
    }

    @Test
    public void emptyList() {
        Student student = new Student(1, "John Doe");
        assertThrows(IndexOutOfBoundsException.class, () -> student.removeSubject(0));
    }
}
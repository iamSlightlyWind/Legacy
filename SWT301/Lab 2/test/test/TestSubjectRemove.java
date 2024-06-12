package test;

import org.junit.Test;
import static org.junit.Assert.*;

import manager.StudentManager;

public class TestSubjectRemove {
    @Test
    public void testRemoveSubject() {
        StudentManager sm = new StudentManager();
        sm.populate();

        assertEquals(-1, sm.student.get(0).removeSubject(99));
        assertEquals(-1, sm.student.get(0).removeSubject(-2));
        assertEquals(1, sm.student.get(0).removeSubject(1));
    }
}

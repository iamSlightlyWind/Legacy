package test;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import student.Student;

public class TestSubjectLookUp {
    private Student student;

    @Before
    public void setUp() {
        student = new Student(1, "John Doe");
    }

    @Test
    public void validInput() {
        String subjectName = student.subjectList(1);
        assertEquals("Java", subjectName);
    }

    @Test
    public void invalidInput() {
        String subjectName = student.subjectList(5);
        assertEquals("", subjectName);
    }
}
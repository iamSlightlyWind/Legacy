import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class StudentManagerTest {

    @Test
    public void testSortStudentsByName() {
        StudentManager sm = new StudentManager();
        sm.populate();

        ArrayList<Student> sortedStudents = sm.sortStudentsByName();

        assertEquals("Adam Damn", sortedStudents.get(0).getName());
        assertEquals("Social Dude", sortedStudents.get(sortedStudents.size() - 1).getName());
    }

    @Test
    public void testSearchStudentsByName() {
        StudentManager sm = new StudentManager();
        sm.populate();

        ArrayList<Student> foundStudents = sm.searchStudentsByName("John", sm.student);

        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("John Doe")));
        assertTrue(foundStudents.stream().anyMatch(student -> student.getName().equals("Alex Johnson")));
    }

    @Test
    public void testFoundStudent() {
        StudentManager sm = new StudentManager();
        sm.populate();

        String studentName = sm.foundStudent(17);
        assertEquals("Tìm thấy sinh viên: Jane Smith", studentName);

        studentName = sm.foundStudent(999);
        assertEquals("", studentName);
    }

    @Test
    public void testAddSubject() {
        StudentManager sm = new StudentManager();
        sm.populate();

        assertEquals(-1, sm.student.get(0).addSubject(1, 2));
        assertEquals(1, sm.student.get(0).addSubject(2, 2));
    }

    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        sm.populate();
        //System.out.println(sm.student.get(0).addSubject(1, 2));
        while (true) {
            sm.printMenu();
        }
    }
}
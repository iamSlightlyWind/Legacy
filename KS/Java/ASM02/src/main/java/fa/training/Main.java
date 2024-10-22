package fa.training;

import fa.training.dao.impl.StudentDaoImpl;
import fa.training.dao.impl.TeacherDaoImpl;
import fa.training.entities.Student;
import fa.training.entities.Teacher;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        StudentDaoImpl sDao = new StudentDaoImpl();
        TeacherDaoImpl tDao = new TeacherDaoImpl();

/*         Teacher teacher = tDao.findById(2l).orElse(null);
        teacher.setId(7l);

        System.out.println(tDao.insertQuery()); */

        Teacher teacher1 = new Teacher(2L, "Alice Smith", "Female", "123-456-7890", "alice.smith@example.com", 50000.0, 5000.0);
        Teacher teacher2 = new Teacher(12L, "Bob Johnson", "Male", "098-765-4321", "bob.johnson@example.com", 55000.0, 4500.0);
        Student student1 = new Student(9L, "John Doe", "Male", "111-222-3333", "john.doe@example.com", "S12345", 85.0, 90.0);
        Student student2 = new Student(11L, "Jane Smith", "Female", "444-555-6666", "jane.smith@example.com", "S67890", 88.0, 92.0);

        tDao.insert(teacher2);
        sDao.insert(student2);
        tDao.insert(teacher1);
        sDao.insert(student1);

        /* teacher.setBasicSalary(5231);

        Student student = sDao.findById(1l).orElse(null);
        student.setPhone("zczxc");

        sDao.update(student);
        tDao.update(teacher); */
    }
}

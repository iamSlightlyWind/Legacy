package fa.training;

import fa.training.dao.StudentDao;
import fa.training.dao.impl.StudentDaoImpl;
import fa.training.entities.Student;
import fa.training.main.PersonManage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDaoImpl();
        List<Student> students = dao.findAll();
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

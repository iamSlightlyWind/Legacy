package fa.training;

import fa.training.dao.StudentDao;
import fa.training.dao.impl.StudentDaoImpl;
import fa.training.entities.Student;
import fa.training.entities.Teacher;
import fa.training.main.PersonManage;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
         * StudentDao dao = new StudentDaoImpl();
         * List<Student> students = dao.findAll();
         * for (Student s : students) {
         * System.out.println(s);
         * }
         */

        Student a = new Student(5l, "Erik", "gay as fuck", "+84371234567", "whatis.a@a.com", "s05", 3.5, 2.5);
        String[][] resulta = a.info();

        Teacher b = new Teacher(7l, "Kinda straight" ,"Harp", "+84371234568" , "whereis.b@b.com", 2.5, 3.5);
        String[][] resultb = b.info();

        System.out.println("");
    }
}

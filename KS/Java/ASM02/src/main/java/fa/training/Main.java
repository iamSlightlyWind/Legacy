package fa.training;

import fa.training.dao.StudentDao;
import fa.training.dao.impl.*;
import fa.training.entities.Student;
import fa.training.entities.Teacher;
import fa.training.main.PersonManage;
import fa.training.utils.Database;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*
         * StudentDao dao = new StudentDaoImpl();
         * List<Student> students = dao.findAll();
         * for (Student s : students) {
         * System.out.println(s);
         * }
         */

        Database.test();

        Student a = new Student(1l, "Erik", "gay as fuck", "+84371234567", "whatis.a@a.com", "1", 3.5, 2.5);
        String[][] resulta = a.info();

        Teacher b = new Teacher(7l, "Kinda straight" ,"Harp", "+84371234568" , "whereis.b@b.com", 2.5, 3.5);
        String[][] resultb = b.info();

        System.out.println("");

        StudentDaoImpl studentDao = new StudentDaoImpl();
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        studentDao.build(a.info(), "update");
        //System.out.println(teacherDao.build(b.info(), "update"));
    }
}

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

        Student a = new Student(2l, "Erik", "gay as fuck", "+84371234567", "whatis.a@a.com", "2", 3.5, 5.5);
        Teacher b = new Teacher(7l, "Kinda straight" ,"Harp", "+84371234568" , "whereis.b@b.com", 2.5, 3.5);

        StudentDaoImpl studentDao = new StudentDaoImpl();
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        studentDao.update(a);
        //System.out.println(teacherDao.build(b.info(), "update"));
    }
}

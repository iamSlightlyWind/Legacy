package fa.training;

import fa.training.dao.impl.StudentDaoImpl;
import fa.training.dao.impl.TeacherDaoImpl;
import fa.training.entities.Teacher;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws IllegalAccessException {
        StudentDaoImpl sDao = new StudentDaoImpl();
        TeacherDaoImpl tDao = new TeacherDaoImpl();

        Teacher current = tDao.findById(2l).orElse(null);

        tDao.delete(current);
    }
}

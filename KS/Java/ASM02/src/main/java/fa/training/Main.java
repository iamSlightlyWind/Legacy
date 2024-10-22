package fa.training;

import fa.training.dao.impl.StudentDaoImpl;
import fa.training.dao.impl.TeacherDaoImpl;
import fa.training.dao.impl.VehicleDaoImpl;
import fa.training.entities.Student;
import fa.training.entities.Vehicle;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1L, "Erik", "Male", "123456789", "erik@mail.com", "123", 8.0, 9.0);

    }
}

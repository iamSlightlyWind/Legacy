package fa.training.dao;

import fa.training.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student insert(Student student);
    void delete(Student student);

}

package fa.training.dao;

import fa.training.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDao {

    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher insert(Teacher teacher);
    Teacher update(Teacher teacher);
    void delete(Teacher teacher);
    
}

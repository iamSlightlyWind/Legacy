package fa.training.dao.impl;

import java.util.List;
import java.util.Optional;

import fa.training.dao.CRUD;
import fa.training.dao.TeacherDao;
import fa.training.entities.Teacher;

public class TeacherDaoImpl implements TeacherDao, CRUD {

    @Override
    public List<Teacher> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(Teacher teacher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
        
}

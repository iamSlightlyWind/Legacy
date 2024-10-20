package fa.training.dao.impl;

import fa.training.dao.StudentDao;
import fa.training.entities.Student;
import fa.training.utils.JavaM301DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = JavaM301DbContext.getConnection();
            String query = buildFindAllQueryString();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private Student mapResultSetToStudent(final ResultSet resultSet) throws SQLException {
        Long id         = resultSet.getLong("id");
        String name     = resultSet.getString("name");
        String phone    = resultSet.getString("phone");
        String email    = resultSet.getString("email");
        String gender   = resultSet.getString("gender");
        String sId      = resultSet.getString("student_id");
        Double theory   = resultSet.getDouble("theory");
        Double practice = resultSet.getDouble("practice");
        return new Student(id, name, gender, phone, email, sId, theory, practice);
    }

    private String buildFindAllQueryString() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT ");
        query.append(" id, name, gender, phone ");
        query.append(", email, student_id, theory, practice ");
        query.append(" FROM student ");
        query.append(" ORDER BY id ASC ");
        return query.toString();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Student insert(Student student) {
        return null;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public void delete(Student student) {

    }
}

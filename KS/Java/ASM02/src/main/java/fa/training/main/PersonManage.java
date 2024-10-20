package fa.training.main;

import fa.training.entities.Person;
import fa.training.entities.Student;
import fa.training.entities.Teacher;
import fa.training.utils.InputValidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonManage {
    private List<Person> persons;

    public PersonManage() {
        persons = new ArrayList<>();
    }

    public void addNewStudent() {
        Student s = new Student();
        persons.add(s);
        System.out.println("New student added");
    }

    public void addNewTeacher() {
        Teacher t = new Teacher();
        persons.add(t);
        System.out.println("New teacher added");
    }

    public void updateStudent() {
        String studentId = InputValidate.inputString("Enter student ID to search:");
        Optional<Student> optional = findByStudentId(studentId);
        if (optional.isPresent()) {
            Student student = optional.get();
            processUpdateStudent(student);
            System.out.println("Student updated");
        } else {
            System.out.println("Student with id " + studentId + " not found");
        }
    }

    private void processUpdateStudent(Student student) {

    }

    public Optional<Student> findByStudentId(String studentId) {
        for (Person person : persons) {
            if (person instanceof Student) {
                Student student = (Student) person;
                if (student.getStudentId().trim().equalsIgnoreCase(studentId.trim())) {
                    return Optional.of(student);
                }
            }
        }
        return Optional.empty();
    }
}

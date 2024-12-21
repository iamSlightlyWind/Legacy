package tmo.ks.asm1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findById(int id);
}
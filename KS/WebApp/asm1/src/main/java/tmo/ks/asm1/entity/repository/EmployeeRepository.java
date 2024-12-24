package tmo.ks.asm1.entity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tmo.ks.asm1.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByPhoneNumber(String phoneNumber);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %?1% OR e.lastName LIKE %?1% OR CONCAT(e.firstName, ' ', e.lastName) LIKE %?1%")
    List<Employee> findByNameContaining(String name);

    List<Employee> findByPhoneNumberContaining(String phoneNumber);

    List<Employee> findByAddressContaining(String address);

    @Query("SELECT e FROM Employee e WHERE e.department.name LIKE %?1%")
    List<Employee> findByDepartmentNameContaining(String departmentName);
}
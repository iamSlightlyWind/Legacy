package tmo.ks.asm1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.service.DatabaseService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    @PostMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return DatabaseService.instance.employeeRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/add")
    public boolean addEmployee(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> employeeData = (Map<String, Object>) requestData.get("employee");
        Map<String, Object> departmentData = (Map<String, Object>) employeeData.get("department");
        Department department = DatabaseService.instance.departmentRepository.findById((String) departmentData.get("name")).get();

        Employee employee = new Employee();
        employee.setFirstName((String) employeeData.get("firstName"));
        employee.setLastName((String) employeeData.get("lastName"));
        employee.setPhoneNumber((String) employeeData.get("phoneNumber"));
        employee.setDateOfBirth(Date.valueOf((String) employeeData.get("dateOfBirth")));
        employee.setGender((int) employeeData.get("gender"));
        employee.setAddress((String) employeeData.get("address"));
        employee.setDepartment(department);
        employee.setRemarks((String) employeeData.get("remarks"));

        Account account = new Account();
        account.setAccount((String) requestData.get("account"));
        account.setEmail((String) requestData.get("email"));
        account.setPassword((String) requestData.get("password"));
        account.setStatus((int) requestData.get("status"));
        account.setEmployee(employee);

        if (DatabaseService.instance.accountRepository.findByAccount(account.getAccount()) != null) {
            return false;
        }

        if (DatabaseService.instance.employeeRepository.findByPhoneNumber(employee.getPhoneNumber()) != null) {
            return false;
        }

        DatabaseService.instance.employeeRepository.save(employee);
        DatabaseService.instance.accountRepository.save(account);

        return true;
    }
}
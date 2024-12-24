package tmo.ks.asm1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.AccountPermission;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.entity.Permission;
import tmo.ks.asm1.service.DatabaseService;
import tmo.ks.asm1.service.PageService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeeRestController {
    final static int pageSize = 8;

    @PostMapping("/api/user/employee/get")
    public Map<String, Object> getEmployees(@RequestBody Map<String, Object> requestData) {
        int page = (int) requestData.get("page");
        String input = (String) requestData.get("input");
        String filter = (String) requestData.get("filter");

        List<Employee> result;
        int maxPage;

        if (input == null || input.isEmpty()) {
            result = PageService.getPage(DatabaseService.instance.employeeRepository, pageSize, page);
            maxPage = PageService.maxPage(DatabaseService.instance.employeeRepository, pageSize);
        } else {
            result = filterEmployees(input, filter);
            maxPage = PageService.maxPage(result, pageSize);
            result = PageService.getPage(result, pageSize, page);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("employees", result);
        response.put("maxPage", maxPage);

        return response;
    }

    private List<Employee> filterEmployees(String input, String filter) {
        switch (filter) {
            case "name":
                return DatabaseService.instance.employeeRepository.findByNameContaining(input);
            case "phoneNumber":
                return DatabaseService.instance.employeeRepository.findByPhoneNumberContaining(input);
            case "address":
                return DatabaseService.instance.employeeRepository.findByAddressContaining(input);
            case "department":
                return DatabaseService.instance.employeeRepository.findByDepartmentNameContaining(input);
            default:
                return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/api/admin/employee/add")
    public boolean addEmployee(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> employeeData = (Map<String, Object>) requestData.get("employee");
        Department department = DatabaseService.instance.departmentRepository.findById((String) employeeData.get("department")).get();
        List<String> permissions = (ArrayList<String>) requestData.get("permissions");
        List<Permission> permissionList = new ArrayList<>();

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

        for (String permission : permissions) {
            Permission p = DatabaseService.instance.permissionRepository.findByName(permission);
            if (p != null) {
                permissionList.add(p);
            } else {
                return false;
            }
        }

        DatabaseService.instance.employeeRepository.save(employee);
        DatabaseService.instance.accountRepository.save(account);
        for (Permission p : permissionList) {
            DatabaseService.instance.accountPermissionRepository.save(new AccountPermission(account, p));
        }

        return true;
    }
}
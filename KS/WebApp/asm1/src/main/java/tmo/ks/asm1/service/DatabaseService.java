package tmo.ks.asm1.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.AccountPermission;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.entity.Permission;
import tmo.ks.asm1.entity.PermissionEndpoint;
import tmo.ks.asm1.entity.repository.AccountPermissionRepository;
import tmo.ks.asm1.entity.repository.AccountRepository;
import tmo.ks.asm1.entity.repository.EmployeeRepository;
import tmo.ks.asm1.entity.repository.PermissionEndpointRepository;
import tmo.ks.asm1.entity.repository.PermissionRepository;

@Service
public class DatabaseService {

    private static DatabaseService instance;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountPermissionRepository accountPermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionEndpointRepository permissionEndpointRepository;

    @PostConstruct
    public void init() {
        instance = this;
        populateData();
    }

    public static void populateData() {
        List<Employee> employees = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        List<PermissionEndpoint> permissionEndpoints = new ArrayList<>();
        List<AccountPermission> accountPermissions = new ArrayList<>();

        employees.add(new Employee("John", "Doe", 1, Date.valueOf("1990-01-15"), "1234567890", "123 Elm St", "IT", "Hardworking"));
        employees.add(new Employee("Jane", "Smith", 2, Date.valueOf("1992-03-22"), "9876543210", "456 Maple Ave", "HR", ""));
        employees.add(new Employee("Alice", "Brown", 2, Date.valueOf("1988-07-09"), "5556667777", "789 Oak Blvd", "Finance", "Experienced"));
        employees.add(new Employee("Bob", "White", 1, Date.valueOf("1995-11-02"), "4443332222", "321 Pine Lane", "Marketing", ""));
        employees.add(new Employee("Charlie", "Black", 1, Date.valueOf("1985-05-30"), "1112223333", "654 Cedar Rd", "Sales", "Consistent"));
        employees.add(new Employee("Admin", "Admin", 1, Date.valueOf("1990-01-15"), "1234567890", "123 Elm St", "IT", "Super Duper Admin"));
        
        accounts.add(new Account("JohnD1", "johndoe@example.com", "password123", 1, employees.get(0)));
        accounts.add(new Account("JaneS1", "janesmith@example.com", "password123", 1, employees.get(1)));
        accounts.add(new Account("AliceB1", "alicebrown@example.com", "password123", 1, employees.get(2)));
        accounts.add(new Account("BobW1", "bobwhite@example.com", "password123", 1, employees.get(3)));
        accounts.add(new Account("CharlieB1", "charlieblack@example.com", "password123", 1, employees.get(4)));
        accounts.add(new Account("AdminA1", "admin@example.com", "adminpassword", 1, employees.get(5)));

        permissions.add(new Permission("USER"));
        permissions.add(new Permission("ADMIN"));

        permissionEndpoints.add(new PermissionEndpoint("/user/**", permissions.get(0)));
        permissionEndpoints.add(new PermissionEndpoint("/api/user/**", permissions.get(0)));
        permissionEndpoints.add(new PermissionEndpoint("/admin/**", permissions.get(1)));
        permissionEndpoints.add(new PermissionEndpoint("/api/admin/**", permissions.get(1)));

        accountPermissions.add(new AccountPermission(accounts.get(0), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(1), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(2), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(3), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(4), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(5), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(5), permissions.get(1)));

        for (Employee employee : employees) {
            instance.employeeRepository.save(employee);
        }

        for (Account account : accounts) {
            instance.accountRepository.save(account);
        }

        for (Permission permission : permissions) {
            instance.permissionRepository.save(permission);
        }

        for (AccountPermission accountPermission : accountPermissions) {
            instance.accountPermissionRepository.save(accountPermission);
        }

        for (PermissionEndpoint permissionEndpoint : permissionEndpoints) {
            instance.permissionEndpointRepository.save(permissionEndpoint);
        }
    }
}
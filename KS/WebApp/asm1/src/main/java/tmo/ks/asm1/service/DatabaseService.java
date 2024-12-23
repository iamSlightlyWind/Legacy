package tmo.ks.asm1.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.AccountPermission;
import tmo.ks.asm1.entity.Department;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.entity.Permission;
import tmo.ks.asm1.entity.PermissionEndpoint;
import tmo.ks.asm1.entity.repository.AccountPermissionRepository;
import tmo.ks.asm1.entity.repository.AccountRepository;
import tmo.ks.asm1.entity.repository.DepartmentRepository;
import tmo.ks.asm1.entity.repository.EmployeeRepository;
import tmo.ks.asm1.entity.repository.PermissionEndpointRepository;
import tmo.ks.asm1.entity.repository.PermissionRepository;

@Service
public class DatabaseService {

    public static DatabaseService instance;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountPermissionRepository accountPermissionRepository;

    @Autowired
    public PermissionRepository permissionRepository;

    @Autowired
    public PermissionEndpointRepository permissionEndpointRepository;

    @Autowired
    public DepartmentRepository departmentRepository;

    @PostConstruct
    public void init() {
        instance = this;
        populateData();

        List<Employee> page = Page.getPage(employeeRepository, 50, 2);
        System.out.println();
    }

    public static void populateData() {
        List<Department> departments = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        List<PermissionEndpoint> permissionEndpoints = new ArrayList<>();
        List<AccountPermission> accountPermissions = new ArrayList<>();

        departments.add(new Department("Sales"));
        departments.add(new Department("Marketing"));
        departments.add(new Department("Engineering"));
        departments.add(new Department("Finance"));
        departments.add(new Department("HR"));
        departments.add(new Department("IT"));

        employees.add(new Employee("Alice", "Brown", 1, Date.valueOf("1985-04-12"), "1112233445", "789 Pine St", departments.get(0), "Hardworking"));
        employees.add(new Employee("Bob", "Green", 0, Date.valueOf("1990-06-18"), "1122334455", "234 Oak St", departments.get(1), "Creative"));
        employees.add(new Employee("Charlie", "White", 1, Date.valueOf("1993-01-22"), "1133445566", "567 Birch St", departments.get(2), "Innovative"));
        employees.add(new Employee("Diana", "Blue", 0, Date.valueOf("1988-10-05"), "1144556677", "890 Cedar St", departments.get(3), "Analytical"));
        employees.add(new Employee("Eve", "Gray", 0, Date.valueOf("1995-02-27"), "1155667788", "123 Cherry St", departments.get(4), "Empathetic"));
        employees.add(new Employee("Frank", "Yellow", 1, Date.valueOf("1987-07-15"), "1166778899", "345 Willow St", departments.get(5), "Detail-oriented"));
        employees.add(new Employee("Grace", "Red", 0, Date.valueOf("1992-11-19"), "1177889900", "678 Fir St", departments.get(2), "Strategic"));
        employees.add(new Employee("Henry", "Pink", 1, Date.valueOf("1990-12-08"), "1188990011", "910 Spruce St", departments.get(1), "Goal-driven"));
        employees.add(new Employee("Ivy", "Purple", 0, Date.valueOf("1994-03-29"), "1199001122", "246 Redwood St", departments.get(2), "Efficient"));
        employees.add(new Employee("Jack", "Silver", 1, Date.valueOf("1986-08-21"), "1200112233", "135 Maple St", departments.get(0), "Persistent"));
        employees.add(new Employee("Kara", "Gold", 0, Date.valueOf("1991-05-16"), "1211223344", "579 Pine St", departments.get(2), "Creative thinker"));
        employees.add(new Employee("Liam", "Copper", 1, Date.valueOf("1993-07-11"), "1222334455", "804 Oak St", departments.get(3), "Problem-solver"));
        employees.add(new Employee("Mona", "Bronze", 0, Date.valueOf("1994-09-30"), "1233445566", "222 Cedar St", departments.get(4), "Organized"));
        employees.add(new Employee("Nina", "Platinum", 0, Date.valueOf("1996-01-05"), "1244556677", "333 Cherry St", departments.get(3), "Meticulous"));
        employees.add(new Employee("Oscar", "Diamond", 1, Date.valueOf("1989-04-13"), "1255667788", "111 Birch St", departments.get(5), "Efficient"));
        employees.add(new Employee("Paul", "Emerald", 1, Date.valueOf("1990-09-09"), "1266778899", "444 Cedar St", departments.get(0), "Adaptable"));
        employees.add(new Employee("Quinn", "Jade", 1, Date.valueOf("1992-12-23"), "1277889900", "789 Fir St", departments.get(2), "Logical"));
        employees.add(new Employee("Rachel", "Ruby", 0, Date.valueOf("1995-05-03"), "1288990011", "101 Spruce St", departments.get(1), "Persuasive"));
        employees.add(new Employee("Steve", "Topaz", 1, Date.valueOf("1993-10-19"), "1299001122", "123 Pine St", departments.get(2), "Collaborative"));
        employees.add(new Employee("Admin", "Admin", 1, Date.valueOf("1990-01-15"), "1234567890", "123 Elm St", departments.get(5), "Super Duper Admin"));

        accounts.add(new Account("AliceB1", "alicebrown@example.com", "password123", 1, employees.get(0)));
        accounts.add(new Account("BobG1", "bobgreen@example.com", "password123", 1, employees.get(1)));
        accounts.add(new Account("CharlieW1", "charliewhite@example.com", "password123", 1, employees.get(2)));
        accounts.add(new Account("DianaB1", "dianablue@example.com", "password123", 1, employees.get(3)));
        accounts.add(new Account("EveG1", "evegray@example.com", "password123", 1, employees.get(4)));
        accounts.add(new Account("FrankY1", "frankyellow@example.com", "password123", 1, employees.get(5)));
        accounts.add(new Account("GraceR1", "gracered@example.com", "password123", 1, employees.get(6)));
        accounts.add(new Account("HenryP1", "henrypink@example.com", "password123", 1, employees.get(7)));
        accounts.add(new Account("IvyP1", "ivypurple@example.com", "password123", 1, employees.get(8)));
        accounts.add(new Account("JackS1", "jacksilver@example.com", "password123", 1, employees.get(9)));
        accounts.add(new Account("KaraG1", "karagold@example.com", "password123", 1, employees.get(10)));
        accounts.add(new Account("LiamC1", "liamcopper@example.com", "password123", 1, employees.get(11)));
        accounts.add(new Account("MonaB1", "monabronze@example.com", "password123", 1, employees.get(12)));
        accounts.add(new Account("NinaP1", "ninaplatinum@example.com", "password123", 1, employees.get(13)));
        accounts.add(new Account("OscarD1", "oscardiamond@example.com", "password123", 1, employees.get(14)));
        accounts.add(new Account("PaulE1", "paulemerald@example.com", "password123", 1, employees.get(15)));
        accounts.add(new Account("QuinnJ1", "quinnjade@example.com", "password123", 1, employees.get(16)));
        accounts.add(new Account("RachelR1", "rachelruby@example.com", "password123", 1, employees.get(17)));
        accounts.add(new Account("SteveT1", "stevetopaz@example.com", "password123", 1, employees.get(18)));
        accounts.add(new Account("AdminA1", "admin@example.com", "adminpassword", 1, employees.get(19)));

        permissions.add(new Permission("USER"));
        permissions.add(new Permission("ADMIN"));
        permissions.add(new Permission("well"));
        permissions.add(new Permission("this"));
        permissions.add(new Permission("is"));
        permissions.add(new Permission("awkward"));
        permissions.add(new Permission("isnt"));
        permissions.add(new Permission("it"));

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
        accountPermissions.add(new AccountPermission(accounts.get(6), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(7), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(8), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(9), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(10), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(11), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(12), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(13), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(14), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(15), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(16), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(17), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(18), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(19), permissions.get(0)));
        accountPermissions.add(new AccountPermission(accounts.get(19), permissions.get(1)));

        for (Department department : departments) {
            instance.departmentRepository.save(department);
        }

        for (Employee employee : employees) {
            instance.employeeRepository.save(employee);
        }

        for (Account account : accounts) {
            instance.accountRepository.save(account);
        }

        for (Permission permission : permissions) {
            instance.permissionRepository.save(permission);
        }

        for (PermissionEndpoint permissionEndpoint : permissionEndpoints) {
            instance.permissionEndpointRepository.save(permissionEndpoint);
        }

        for (AccountPermission accountPermission : accountPermissions) {
            instance.accountPermissionRepository.save(accountPermission);
        }
    }
}
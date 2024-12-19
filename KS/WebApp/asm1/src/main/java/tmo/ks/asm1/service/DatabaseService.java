package tmo.ks.asm1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import tmo.ks.asm1.entity.Account;
import tmo.ks.asm1.entity.Employee;
import tmo.ks.asm1.repository.AccountRepository;
import tmo.ks.asm1.repository.EmployeeRepository;

@Service
public class DatabaseService {
    
    private static DatabaseService instance;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static Employee saveEmployee(Employee employee) {
        return instance.employeeRepository.save(employee);
    }

    public static Account saveEmployee(Account account) {
        return instance.accountRepository.save(account);
    }
}
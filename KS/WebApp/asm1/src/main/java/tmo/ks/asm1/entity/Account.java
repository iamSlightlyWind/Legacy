package tmo.ks.asm1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "account")

@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account", nullable = false, columnDefinition = "VARCHAR(255)")
    private String account;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "status", columnDefinition = "INT")
    private int status;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Account(String account, String email, String password, int status, Employee employee) {
        this.account = account;
        this.email = email;
        this.password = password;
        this.status = status;
        this.employee = employee;
    }
}
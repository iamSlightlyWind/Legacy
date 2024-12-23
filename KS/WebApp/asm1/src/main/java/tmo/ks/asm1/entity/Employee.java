package tmo.ks.asm1.entity;

import java.sql.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")

@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String lastName;

    @Column(name = "gender", nullable = false, columnDefinition = "INT")
    private int gender;

    @Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
    private Date dateOfBirth;

    @Column(name = "phone_number", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @ManyToOne
    @JoinColumn(name = "name", nullable = false)
    private Department department;

    @Column(name = "remark", columnDefinition = "VARCHAR(1000)")
    private String remarks;

    public Employee(String firstName, String lastName, int gender, Date dateOfBirth, String phoneNumber, String address, Department department, String remarks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
        this.remarks = remarks;
    }
}

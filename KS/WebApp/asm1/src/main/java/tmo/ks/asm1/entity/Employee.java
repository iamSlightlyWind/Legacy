package tmo.ks.asm1.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @Id
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

    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(20)")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)")
    private String department;

    @Column(name = "remark", columnDefinition = "VARCHAR(1000)")
    private String remarks;

}

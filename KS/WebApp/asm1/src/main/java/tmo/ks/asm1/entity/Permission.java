package tmo.ks.asm1.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permission")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    public Permission(String name) {
        this.name = name;
    }

    public static String[] extractPermissions(List<AccountPermission> accountPermissions) {
        String[] permissions = new String[accountPermissions.size()];
        for (int i = 0; i < accountPermissions.size(); i++) {
            permissions[i] = accountPermissions.get(i).getPermission().getName();
        }
        return permissions;
    }
}
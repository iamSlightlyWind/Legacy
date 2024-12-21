package tmo.ks.asm1.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_permission")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPermission {

    @EmbeddedId
    private AccountPermissionId id;

    @ManyToOne
    @MapsId("account_id")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("permission_id")
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public AccountPermission(Account account, Permission permission) {
        this.account = account;
        this.permission = permission;
        this.id = new AccountPermissionId(account.getId(), permission.getId());
    }
}
package walking.footwear.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity
public class Role implements Serializable {
//    CUSTOMER("ROLE_CUSTOMER"),MANAGER("ROLE_MANAGER"),EMPLOYEE("ROLE_EMPLOYEE"),ADMIN("ROLE_ADMIN");
//    @Enumerated(EnumType.STRING)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;
    @Enumerated(EnumType.STRING)
    private ERole role;

    public Role() {
    }

    public Role(ERole role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    private String roleName;

    public Role(String role) {
        this.roleName = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleName, role1.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}

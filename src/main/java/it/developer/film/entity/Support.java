package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class Support {

    @Id
    private String supportName;

    public Support(String supportName) {
        this.supportName = supportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Support support = (Support) o;
        return Objects.equals(supportName, support.supportName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportName);
    }
}

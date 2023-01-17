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
public class Nationality {

    @Id
    private String nationalityName;

    public Nationality(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nationality that = (Nationality) o;
        return Objects.equals(nationalityName, that.nationalityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalityName);
    }
}

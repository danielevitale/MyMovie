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
public class Genre {

    @Id
    private String genreName;

    public Genre(String genre) {
        this.genreName = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return Objects.equals(genreName, genre1.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName);
    }
}

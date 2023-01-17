package it.developer.film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class SupportMovie {

    @EmbeddedId
    private SupportMovieId supportMovieId;

    private long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportMovie that = (SupportMovie) o;
        return Objects.equals(supportMovieId, that.supportMovieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportMovieId);
    }
}

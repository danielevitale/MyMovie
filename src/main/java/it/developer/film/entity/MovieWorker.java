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
@NoArgsConstructor
@AllArgsConstructor
public class MovieWorker {

    @EmbeddedId
    private MovieWorkerId movieWorkerId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWorker that = (MovieWorker) o;
        return Objects.equals(movieWorkerId, that.movieWorkerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieWorkerId);
    }
}

package it.developer.film.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieWorkerId implements Serializable {

    @ManyToOne
    @JoinColumn( name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn( name = "worker_id")
    private Worker worker;

    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWorkerId that = (MovieWorkerId) o;
        return Objects.equals(movie, that.movie) && Objects.equals(worker, that.worker) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, worker, role);
    }
}

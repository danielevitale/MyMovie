package it.developer.film.repository;

import it.developer.film.entity.MovieWorker;
import it.developer.film.entity.MovieWorkerId;
import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerResponseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieWorkerRepository extends JpaRepository<MovieWorker, MovieWorkerId> {

    @Query(value="SELECT new it.developer.film.payload.response.WorkerResponseDetails (w.id, " +
            "w.firstName, " +
            "w.lastName, " +
            "w.image, " +
            "mw.movieWorkerId.role) " +
            "FROM MovieWorker mw " +
            "INNER JOIN Worker w on mw.movieWorkerId.worker.id = w.id " +
            "WHERE mw.movieWorkerId.movie.id = :movieId")
    List<WorkerResponseDetails> getWorkerByMovie(@Param("movieId") long id);

}

package it.developer.film.repository;

import it.developer.film.entity.MovieWorker;
import it.developer.film.entity.MovieWorkerId;
import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerMovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieWorkerRepository extends JpaRepository<MovieWorker, MovieWorkerId> {

    @Query(value="SELECT new it.developer.film.payload.response.WorkerMovieResponse (w.id, " +
            "w.firstName, " +
            "w.lastName, " +
            "mw.movieWorkerId.role) " +
            "FROM MovieWorker mw " +
            "INNER JOIN Worker w on mw.movieWorkerId.worker.id = w.id " +
            "WHERE mw.movieWorkerId.movie.id = :movieId")
    List<WorkerMovieResponse> getWorkerByMovie(@Param("movieId") long id);

}

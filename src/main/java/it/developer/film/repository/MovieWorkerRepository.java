package it.developer.film.repository;

import it.developer.film.entity.MovieWorker;
import it.developer.film.entity.MovieWorkerId;
import it.developer.film.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieWorkerRepository extends JpaRepository<MovieWorker, MovieWorkerId> {

}

package it.developer.film.controller;

import it.developer.film.entity.*;
import it.developer.film.service.MovieService;
import it.developer.film.service.MovieWorkerService;
import it.developer.film.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("movieWorker")
public class MovieWorkerController {

    @Autowired
    MovieWorkerService movieWorkerService;
    @Autowired
    MovieService movieService;
    @Autowired
    WorkerService workerService;

    // metodo per associare a un film un eventuale (attore, attrice, regista) indicandone la mansione svolta
    @PutMapping("/{movId}/{worId}/{role}")
    public ResponseEntity<?> assoc(@PathVariable long movId, @PathVariable long worId, @PathVariable String role) {

        Optional<Worker> w = workerService.findById(worId);
        if (w.isEmpty()){
            return new ResponseEntity("Worker not found", HttpStatus.NOT_FOUND);
        }
        Optional<Movie> m = movieService.findById(movId);
        if (m.isEmpty()){
            return new ResponseEntity("Movie not found", HttpStatus.NOT_FOUND);
        }
        if(!movieWorkerService.contains(role)){
            return new ResponseEntity("Role not found", HttpStatus.NOT_FOUND);
        }
        MovieWorker movieWorker = new MovieWorker(
            new MovieWorkerId(m.get(), w.get(), Role.valueOf(role))
        );

        movieWorkerService.insertMovieWorker(movieWorker);
        return new ResponseEntity<>("the operation run!", HttpStatus.OK);
    }
}

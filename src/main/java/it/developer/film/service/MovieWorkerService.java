package it.developer.film.service;

import it.developer.film.entity.Locality;
import it.developer.film.entity.MovieWorker;
import it.developer.film.entity.Role;
import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerResponseDetails;
import it.developer.film.repository.MovieWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieWorkerService {
    @Autowired
    MovieWorkerRepository movieWorkerRepository;

    public void insertMovieWorker(MovieWorker movieWorker){
        movieWorkerRepository.save(movieWorker);
    }

    public boolean contains(String role) {
        boolean trovato =false;

        for (Role r : Role.values()) {
            if (r.name().equals(role)) {
                trovato = true;
            }
        }
        return trovato;
    }

    public List<WorkerResponseDetails> getWorkerByMovie(long id){
       return movieWorkerRepository.getWorkerByMovie(id);
    }



}

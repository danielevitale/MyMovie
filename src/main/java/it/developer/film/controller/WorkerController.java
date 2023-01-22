package it.developer.film.controller;

import it.developer.film.entity.Locality;
import it.developer.film.entity.LocalityId;
import it.developer.film.entity.Nationality;
import it.developer.film.entity.Worker;
import it.developer.film.payload.request.WorkerRequest;
import it.developer.film.service.NationalityService;
import it.developer.film.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;
    @Autowired
    NationalityService nationalityService;


    @PutMapping("/insert")
    public ResponseEntity<?>insertWorker(@RequestBody WorkerRequest worker){

       //Optional<Nationality> nat = nationalityService.existsByNationalityName(worker.getNationalityName());

        if(workerService.existsByFirstName(worker.getFirstName()) &&
            workerService.existsByLastName(worker.getLastName()) &&
            workerService.existsByBirthday(worker.getBirthday())){
            return new ResponseEntity<String>("Worker already exists", HttpStatus.BAD_REQUEST);
        }

        Worker wor = new Worker(
                worker.getFirstName(),
                worker.getLastName(),
                worker.getBirthday(),
                worker.getImage(),
                new Locality(new LocalityId(new Nationality(worker.getNationalityName()), worker.getCityName()))
        );

        workerService.insertWorker(wor);

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }
}

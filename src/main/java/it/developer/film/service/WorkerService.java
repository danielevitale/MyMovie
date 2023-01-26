package it.developer.film.service;

import it.developer.film.entity.Nationality;
import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerResponse;
import it.developer.film.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkerService {
     @Autowired
     WorkerRepository workerRepository;

     public boolean existsByFirstName (String firstName){
          return workerRepository.existsByFirstName(firstName);
     }
     public boolean existsByLastName (String lastName){
          return workerRepository.existsByLastName(lastName);
     }
     public boolean existsByBirthday (LocalDate birthday){
          return workerRepository.existsByBirthday(birthday);
     }

     public void insertWorker(Worker work) {
          workerRepository.save(work);
     }

     public List<WorkerResponse>findAllWorker(){
          return workerRepository.findAllWorker();
     }

}

package it.developer.film.service;

import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerDetailsResponse;
import it.developer.film.payload.response.WorkerResponse;
import it.developer.film.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
     @Autowired
     WorkerRepository workerRepository;

    public void insertWorker(Worker work) {
          workerRepository.save(work);
     }

     public List<WorkerResponse>findAllWorker(){
          return workerRepository.findAllWorker();
     }
     public Optional<Worker> findById(Long Id){
          return workerRepository.findById(Id);
     }

     public WorkerDetailsResponse getWorkerDetails(long id){
          return workerRepository.getWorkerDetails(id);
     }
     public int getWorkerForCheck(String firstName, String lastName, LocalDate birthday){
          return workerRepository.getWorkerForCheck(firstName, lastName, birthday);
     }

     public int getWorkerForCheckForUpdate(Long id, String firstName, String lastName, LocalDate birthday){
          return workerRepository.getWorkerForCheckForUpdate(id, firstName, lastName, birthday);
     }
}

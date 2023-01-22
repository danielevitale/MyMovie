package it.developer.film.repository;

import it.developer.film.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsByFirstName (String firstName);
    boolean existsByLastName (String lastName);
    boolean existsByBirthday (LocalDate birthday);

}

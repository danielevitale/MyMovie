package it.developer.film.repository;

import it.developer.film.entity.Worker;
import it.developer.film.payload.response.MovieWorkerResponse;
import it.developer.film.payload.response.WorkerDetailsResponse;
import it.developer.film.payload.response.WorkerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsByFirstName (String firstName);
    boolean existsByLastName (String lastName);
    boolean existsByBirthday (LocalDate birthday);

    @Query(value="SELECT new it.developer.film.payload.response.WorkerResponse (" +
            "w.id, " +
            "w.firstName, " +
            "w.lastName" +
            ") FROM Worker AS w " +
            "ORDER BY w.firstName")
    List<WorkerResponse> findAllWorker();

    @Query(value="SELECT new it.developer.film.payload.response.WorkerDetailsResponse (" +
            "w.id, " +
            "w.firstName, " +
            "w.lastName, " +
            "w.birthday, " +
            "w.locality.localityId.cityName, " +
            "w.locality.localityId.nationalityName.nationalityName, " +
            "w.workerImg.filename " +
            ") FROM Worker AS w " +
            "WHERE w.id = :workerId")
    WorkerDetailsResponse getWorkerDetails(@Param("workerId") long workerId);

}

package it.developer.film.repository;

import it.developer.film.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, String> {

    @Query(value = "SELECT * FROM nationality ORDER BY nationality_name", nativeQuery = true)
    Set<String> getAllNationality();

    Optional<Nationality> findById(String nationalityName);

}

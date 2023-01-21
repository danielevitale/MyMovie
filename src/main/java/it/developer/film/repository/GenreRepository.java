package it.developer.film.repository;

import it.developer.film.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {


    @Query(value = "SELECT * FROM genre", nativeQuery = true)
    Set<String> getAllGenre();
}

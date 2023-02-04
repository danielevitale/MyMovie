package it.developer.film.repository;

import it.developer.film.entity.Language;
import it.developer.film.payload.response.MovieDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    Set<Language> findByLanguageNameIn(Set<String> id);

    @Query(value="SELECT l.languageName " +
            "FROM Movie m " +
            "INNER JOIN m.languages l " +
            "WHERE m.id = :movieId")
    Set<String> getLanguagesByMovie(@Param("movieId") long id);



}

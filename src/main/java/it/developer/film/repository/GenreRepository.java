package it.developer.film.repository;

import it.developer.film.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    @Query(value = "SELECT * FROM genre ORDER BY genre_name", nativeQuery = true)
    Set<String> getAllGenre();

   /* @Query(value = "SELECT g.genreName " +
    "FROM Movie m " +
    "INNER JOIN m.genres g " +
    "WHERE m.id = movieId")
    Set<String> getGenresByMovie(@Param("movieId") Long movieId);*/

    @Query(value = "SELECT mg.genre_name " +
    "FROM movie_genre mg " +
    "WHERE mg.movie_id = :movieId", nativeQuery = true)
    Set<String> getGenresByMovie(@Param("movieId") Long movieId);

}

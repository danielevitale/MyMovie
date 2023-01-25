package it.developer.film.repository;

import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);

    boolean existsByProductionYear(LocalDate productionYear);


    List<Movie> findByProductionYearAfter(LocalDate date);

    /*List<Movie> findByDurationAfter(Long duration);*/

   /* @Query(value = "SELECT * FROM movie WHERE duration > :duration", nativeQuery = true)
    List<Movie> getMovieAfterDuration(@Param("duration") Long duration);
    */

    //JPQL
    @Query(value = "SELECT new it.developer.film.payload.response.MovieResponse(" +
            "m.title, " +
            "m.duration " +
            ") FROM Movie m " +
            "WHERE duration > :duration")
    List<MovieResponse> getTitle(@Param("duration") Long duration);

    @Query(value = "SELECT new it.developer.film.payload.response.MovieResponse(" +
            "m.title, " +
            "m.duration " +
            ") FROM Movie m ORDER BY m.title"
    )
    List<MovieResponse> getAllMovie();


}

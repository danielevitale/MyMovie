package it.developer.film.repository;

import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieDetailsResponse;
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


    //List<Movie> findByProductionYearAfter(LocalDate date);

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

    @Query(value = "SELECT m.title " +
            "FROM Movie AS m " +
            "INNER JOIN m.languages AS l " +
            "WHERE l.languageName = :language " +
            "ORDER BY m.title")
    List<String>findByLanguage(@Param("language") String language);


    @Query(value = "SELECT new it.developer.film.payload.response.MovieDetailsResponse (" +
            "m.id, " +
            "m.title, " +
            "m.plot, " +
            "m.productionYear, " +
            "m.duration, " +
            ":imagePath || m.poster, " +
            "m.nationality.nationalityName " +
            ") FROM Movie AS m " +
            "WHERE m.id = :id ")
    MovieDetailsResponse getMovieDetails(@Param("id") long id, @Param("imagePath") String imagePath);

    @Query(value = "SELECT COUNT(m.id) " +
            "FROM movie AS m " +
            "WHERE m.title = :title " +
            "AND m.production_year = :productionYear", nativeQuery = true)
    int getMovieForCheck(@Param("title") String title, @Param("productionYear") LocalDate productionYear);

    @Query(value = "SELECT COUNT(m.id) " +
            "FROM movie AS m " +
            "WHERE m.title = :title " +
            "AND m.production_year = :productionYear " +
            "AND m.id != :id", nativeQuery = true)
    int getMovieForCheckForUpdate(
            @Param("title") String title,
            @Param("productionYear") LocalDate productionYear,
            @Param("id") long id);
}

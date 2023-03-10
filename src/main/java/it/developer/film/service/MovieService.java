package it.developer.film.service;

import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieDetailsResponse;
import it.developer.film.payload.response.MovieResponse;
import it.developer.film.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Value("${movie.path}")
    private String imagePath;

    @Autowired
    MovieRepository movieRepository;

    public void insertMovie(Movie mov) {
        movieRepository.save(mov);
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public boolean existsByTitle(String title) {
        return movieRepository.existsByTitle(title);
    }

    public boolean existsByProductionYear(LocalDate productionYear) {
        return movieRepository.existsByProductionYear(productionYear);
    }

  /*  public List<Movie> findByProductionYearAfter(LocalDate date) {
        return movieRepository.findByProductionYearAfter(date);
    }*/

    public List<MovieResponse> getTitle(Long duration) {
        return movieRepository.getTitle(duration);
    }

    public List<MovieResponse> getAllMovie() {
        List<MovieResponse> movieList = movieRepository.getAllMovie();
        return movieList;
    }

    public List<String>findByLanguage(String language){
        List<String> movieListLan = movieRepository.findByLanguage(language);
        return movieListLan;
    }

    public MovieDetailsResponse getMovieDetails(long id) {
        MovieDetailsResponse movie = movieRepository.getMovieDetails(id, imagePath);
        return movie;
    }

    public int getMovieForCheck(String title, LocalDate productionYear){
        return movieRepository.getMovieForCheck(title, productionYear);
    }

    public int getMovieForCheckForUpdate (String title, LocalDate productionYear, long id){
        return movieRepository.getMovieForCheckForUpdate(title, productionYear, id);
    }


}

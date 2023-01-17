package it.developer.film.service;

import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieResponse;
import it.developer.film.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    @Autowired MovieRepository movieRepository;

    public void insertMovie(Movie mov){movieRepository.save(mov);}

    public boolean existsByTitle(String title){
        return movieRepository.existsByTitle(title);}

    public boolean existsByProductionYear(LocalDate productionYear){
        return movieRepository.existsByProductionYear(productionYear);}

    public List<Movie> findByProductionYearAfter(LocalDate date){
        return movieRepository.findByProductionYearAfter(date);
    }

    /*public List<Movie> findByDurationAfter(Long duration){
        return movieRepository.findByDurationAfter(duration);
    }*/

    /*public List<Movie> getMovieAfterDuration(Long duration){
        return movieRepository.getMovieAfterDuration(duration);
    }*/

    public List<MovieResponse> getTitle(Long duration){
        return movieRepository.getTitle(duration);
    }





}

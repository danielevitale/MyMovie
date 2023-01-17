package it.developer.film.controller;

import it.developer.film.entity.Language;
import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieResponse;
import it.developer.film.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PutMapping("insert")
    public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {
        if (movieService.existsByTitle(movie.getTitle()) && movieService.existsByProductionYear(movie.getProductionYear()))
            return new ResponseEntity<String>("This movie is already exists!!!", HttpStatus.NOT_ACCEPTABLE);

        Movie mov = new Movie(
                movie.getTitle(),
                movie.getPlot(),
                movie.getProductionYear(),
                movie.getDuration(),
                movie.getPoster(),
                movie.getNationality(),
                movie.getLanguages()
        );
        movieService.insertMovie(mov);
        if (mov.getId() == null) {
            return new ResponseEntity<String>("The insertion fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }

    /*@GetMapping("movieAfter")
    public ResponseEntity<?> findByProductionYearAfter(@RequestBody LocalDate year) {
        List<Movie> movieList = movieService.findByProductionYearAfter(year);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);

    }*/

    /*@GetMapping("durationAfter/{duration}")
    public ResponseEntity<?> findByDuration(@PathVariable Long duration) {
        List<Movie> movieList = movieService.findByDurationAfter(duration);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }*/

    /*@GetMapping("durationAfter/{duration}")
    public ResponseEntity<?> getMovieAfterDuration(@PathVariable Long duration) {
        List<Movie> movieList = movieService.getMovieAfterDuration(duration);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }*/

    @GetMapping("duration/{duration}")
    public ResponseEntity<?> getTitle(@PathVariable Long duration){
        List<MovieResponse> movieList = movieService.getTitle(duration);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(movieList, HttpStatus.OK);

    }

    @PatchMapping("Languages")
    public ResponseEntity<?> getTitle(@PathVariable Long id, @RequestParam Set<Language> languages) {



    return null;

    }
}

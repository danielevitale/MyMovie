package it.developer.film.controller;

import it.developer.film.entity.Language;
import it.developer.film.entity.Movie;
import it.developer.film.payload.response.MovieResponse;
import it.developer.film.service.LanguageService;
import it.developer.film.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    LanguageService languageService;

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

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }

    @GetMapping("duration/{duration}")
    public ResponseEntity<?> getTitle(@PathVariable Long duration){
        List<MovieResponse> movieList = movieService.getTitle(duration);
        if (movieList.isEmpty()) {
            return new ResponseEntity<>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @PatchMapping("languages/{id}")
    @Transactional
    public ResponseEntity<?> addLanguages(@PathVariable Long id, @RequestParam Set<String> languages) {

        Optional<Movie> m = movieService.findById(id);

        if (m.isEmpty()){
            return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
        }

        Set<Language> languages1 = languageService.findByLanguageNameIn(languages);

        if(languages1.isEmpty()){
            return new ResponseEntity<>("Language set empty", HttpStatus.BAD_REQUEST);
        }
        m.get().setLanguages(languages1);

        return new ResponseEntity<String>("Lingue modificate", HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllMovie (){
        List<MovieResponse> movieList = movieService.getAllMovie();
        return new ResponseEntity<>(movieList,HttpStatus.OK);
    }

    @GetMapping("/findByLanguage/{language}")
    public ResponseEntity<?> addLanguages(@PathVariable String language){

        List<String> m = movieService.findByLanguage(language);
        if(m.isEmpty()){
            return new ResponseEntity<String>("No movie found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(m,HttpStatus.OK);

    }



}

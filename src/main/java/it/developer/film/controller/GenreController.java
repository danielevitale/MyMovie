package it.developer.film.controller;

import it.developer.film.entity.Genre;
import it.developer.film.service.GenreService;
import org.apache.catalina.LifecycleState;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    // metodo per inserire un nuovo genere
    @PutMapping("insert")
    public ResponseEntity<?> insertGenre(@RequestBody Genre genre) {
        if (genreService.existsByGenre(genre.getGenreName()))
            return new ResponseEntity<>("Already exists", HttpStatus.NOT_ACCEPTABLE);

        Genre genr = new Genre(
                genre.getGenreName()
        );
        genreService.insertGenre(genr);

        return new ResponseEntity<>("Operation run!", HttpStatus.CREATED);
    }

    // metodo per ottenere l'elenco dei generi inseriti
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllGenre() {
        Set<String> gen = genreService.getAllGenre();
        if (gen.isEmpty()) {
            return new ResponseEntity<String>("Set is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gen, HttpStatus.OK);
    }

    @GetMapping("/findOne/{genreName}")
    public ResponseEntity<?> findOne(@PathVariable String genreName) {
        if (genreService.existsByGenre(genreName)) {
            return new ResponseEntity<String>("Genre already exists", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Genre not found", HttpStatus.BAD_REQUEST);
    }


}

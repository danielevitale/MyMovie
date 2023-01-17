package it.developer.film.controller;

import it.developer.film.entity.Genre;
import it.developer.film.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PutMapping("insert")
    public ResponseEntity<?> insertGenre(@RequestBody Genre genre){
        if (genreService.existsByGenre(genre.getGenreName()))
            return new ResponseEntity<>("Already exists", HttpStatus.NOT_ACCEPTABLE);

        Genre genr = new Genre(
                genre.getGenreName()
        );

        genreService.insertGenre(genr);
        if (genreService.existsByGenre(genre.getGenreName())) {
            return new ResponseEntity<>("Operation run!", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Operation failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

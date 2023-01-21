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

        return new ResponseEntity<>("Operation run!", HttpStatus.CREATED);
    }

    // metodo per ottenere l'elenco dei generi inseriti
    @GetMapping("/findAll")
    public ResponseEntity<?>findAll(){
        List<Genre> gen = genreService.findAll();
        if(gen.isEmpty()){
            return new ResponseEntity<String>("List is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gen, HttpStatus.CREATED);
    }


}

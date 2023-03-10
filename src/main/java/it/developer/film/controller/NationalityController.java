package it.developer.film.controller;

import it.developer.film.entity.Nationality;
import it.developer.film.service.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("nationality")
public class NationalityController {

    @Autowired
    NationalityService nationalityService;

    // metodo per inserire una nuova nazionalità
    @PutMapping("insert")
    public ResponseEntity<?> insertNationality(@RequestBody Nationality nat) {
        if (nationalityService.existsByNationalityName(nat.getNationalityName()))
            return new ResponseEntity<>("Nationality already exists!!", HttpStatus.NOT_ACCEPTABLE);

        Nationality nationality = new Nationality(
                nat.getNationalityName()
        );

        nationalityService.insertNationality(nationality);

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }

    // metodo per vedere tutte le nazionalità caricate
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllNationality() {
        Set<String> nat = nationalityService.getAllNationality();
        if (nat.isEmpty()) {
            return new ResponseEntity<String>("Set is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nat, HttpStatus.OK);
    }
    // metodo per vedere se una nazionalità è gia stata inserita
    @GetMapping("/findOne/{nationalityName}")
    public ResponseEntity<?> findOne(@PathVariable String nationalityName) {
        if (nationalityService.existsByNationalityName(nationalityName)) {
            return new ResponseEntity<String>("Nationality already exists", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Nationality not found", HttpStatus.BAD_REQUEST);
    }
}





package it.developer.film.controller;

import it.developer.film.entity.Nationality;
import it.developer.film.service.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nationality")
public class NationalityController {

    @Autowired
    NationalityService nationalityService;

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
}





package it.developer.film.controller;


import it.developer.film.entity.Locality;
import it.developer.film.entity.LocalityId;
import it.developer.film.entity.Nationality;
import it.developer.film.payload.request.LocalityRequest;
import it.developer.film.payload.response.LocalityResponse;
import it.developer.film.service.LocalityService;
import it.developer.film.service.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("locality")
public class LocalityController {
    @Autowired
    LocalityService localityService;
    @Autowired
    NationalityService nationalityService;

    // metodo per inserimento di nuove locality
    @PutMapping("/insert/{nationality}")
    public ResponseEntity<?> insertLocality(@PathVariable String nationality, @RequestBody LocalityRequest locality) {
        Optional<Nationality> nat = nationalityService.findById(nationality);
        if (nat.isEmpty()) {
            return new ResponseEntity<String>("Nationality not found", HttpStatus.BAD_REQUEST);
        }

        if (localityService.findByLocality(nationality, locality.getCityName()) != null) {
            return new ResponseEntity<String>("Locality already exists", HttpStatus.NOT_ACCEPTABLE);
        }


        Locality loc = new Locality(new LocalityId(new Nationality(nationality), locality.getCityName()));

        localityService.insertLocality(loc);

        return new ResponseEntity<String>("The operation run!", HttpStatus.CREATED);
    }
    // metodo per farsi restituire tutte le locality
    @GetMapping("/findAll")
    public ResponseEntity<?> findAllLocality() {
        List<LocalityResponse> getAllLocality = localityService.findAllLocality();
        return new ResponseEntity<>(getAllLocality, HttpStatus.OK);
    }
    // metodo per vedere se una locality è gia presente
    @GetMapping("/findOne/{nationalityName}/{cityName}")
    public ResponseEntity<?> findOneLocality(@PathVariable Nationality nationalityName, @PathVariable String cityName) {
        if (localityService.existsByNationalityNameAndCityName(nationalityName, cityName)) {
            return new ResponseEntity<String>("Locality already exists", HttpStatus.OK);
        }
        return new ResponseEntity<>("Locality not found", HttpStatus.NOT_FOUND);

    }


}

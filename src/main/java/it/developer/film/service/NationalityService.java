package it.developer.film.service;

import it.developer.film.entity.Movie;
import it.developer.film.entity.Nationality;
import it.developer.film.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NationalityService {

    @Autowired
    NationalityRepository nationalityRepository;

    public void insertNationality(Nationality nat) {
        nationalityRepository.save(nat);
    }

    public boolean existsByNationalityName(String nationalityName) {
        return nationalityRepository.existsById(nationalityName);
    }
}

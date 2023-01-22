package it.developer.film.service;

import it.developer.film.entity.Locality;
import it.developer.film.entity.LocalityId;
import it.developer.film.entity.Nationality;
import it.developer.film.payload.request.LocalityRequest;
import it.developer.film.repository.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Set;

@Service
public class LocalityService {

    @Autowired
    LocalityRepository localityRepository;

    public void insertLocality(Locality locality){
        localityRepository.save(locality);
    }

    public Locality findByLocality(String nationality, String city){
        return localityRepository.findByLocality(nationality, city);
    }

   /* public boolean existsByNationalityNameAndCityName(String nationality, String city){
        return localityRepository.existsByNationalityNameAndCityName(nationality, city);
    }*/

}

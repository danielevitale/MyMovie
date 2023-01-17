package it.developer.film.service;

import it.developer.film.entity.Genre;
import it.developer.film.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {


    @Autowired
    GenreRepository genreRepository;

    public void insertGenre(Genre genre){
        genreRepository.save(genre);
    }

    public boolean existsByGenre(String genre){
        return genreRepository.existsById(genre);
    }

}

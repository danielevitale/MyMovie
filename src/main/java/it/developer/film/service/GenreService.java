package it.developer.film.service;

import it.developer.film.entity.Genre;
import it.developer.film.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Set<String> getAllGenre(){
        return  genreRepository.getAllGenre();
    }

    public Set<String> getGenresByMovie(long id){ return genreRepository.getGenresByMovie(id); }

}

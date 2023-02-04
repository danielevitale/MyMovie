package it.developer.film.service;

import it.developer.film.entity.Language;
import it.developer.film.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public Set<Language> findByLanguageNameIn(Set<String> id){
        return languageRepository.findByLanguageNameIn(id);
    }
    public Set<String> getLanguagesByMovie(Long id){
        return languageRepository.getLanguagesByMovie(id);
    }
}

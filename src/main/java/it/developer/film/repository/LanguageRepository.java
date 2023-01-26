package it.developer.film.repository;

import it.developer.film.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

    Set<Language> findByLanguageNameIn(Set<String> id);


}

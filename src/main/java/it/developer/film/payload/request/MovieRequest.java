package it.developer.film.payload.request;

import it.developer.film.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter @AllArgsConstructor @NoArgsConstructor
public class MovieRequest {

    private String title;
    private String plot;
    private LocalDate productionYear;
    private int duration;
    private String nationalityName;

    private Set<Genre> genres;


}

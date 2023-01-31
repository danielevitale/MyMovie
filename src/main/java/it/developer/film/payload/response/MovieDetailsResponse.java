package it.developer.film.payload.response;

import it.developer.film.entity.Genre;
import it.developer.film.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MovieDetailsResponse {

    private Long Id;

    private String title;

    private String plot;

    private LocalDate productionYear;

    private long duration;

    private String poster;

    private String nationalityName;

    private Set<Language> languages;

    private Set<Genre> genres;

    private List<WorkerResponseDetails> workers;

    public MovieDetailsResponse(Long id, String title, String plot, LocalDate productionYear, long duration, String poster, String nationalityName) {
        Id = id;
        this.title = title;
        this.plot = plot;
        this.productionYear = productionYear;
        this.duration = duration;
        this.poster = poster;
        this.nationalityName = nationalityName;
    }
}

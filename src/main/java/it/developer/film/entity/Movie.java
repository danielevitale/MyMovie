package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = true,  columnDefinition = "TEXT")
    private String plot;

    private LocalDate productionYear;

    @Column(nullable = false)
    private long duration;

    private String poster;

    @ManyToOne
    @JoinColumn(name = "Nationality", nullable = false)
    private Nationality nationality;

    @ManyToMany
    @JoinTable(name = "movie_language",
            joinColumns = {@JoinColumn(name = "movie_Id", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "language_name", referencedColumnName = "languageName")})
    Set<Language> languages;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_name", referencedColumnName = "genreName")})
    Set<Genre> genres;

    public Movie(String title, String plot, LocalDate productionYear, long duration, String poster, Nationality nationality, Set<Language> languages) {
        this.title = title;
        this.plot = plot;
        this.productionYear = productionYear;
        this.duration = duration;
        this.poster = poster;
        this.nationality = nationality;
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Id == movie.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}

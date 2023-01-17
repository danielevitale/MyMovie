package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Language {
    @Id
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(languageName, language.languageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageName);
    }
}

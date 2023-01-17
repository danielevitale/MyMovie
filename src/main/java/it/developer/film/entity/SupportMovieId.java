package it.developer.film.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupportMovieId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "support_name", nullable = false)
    private Support support;

    @ManyToOne
    @JoinColumn(name = "film_Id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "currency_name", referencedColumnName = "currencyName")
    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportMovieId that = (SupportMovieId) o;
        return Objects.equals(support, that.support) && Objects.equals(movie, that.movie) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(support, movie, currency);
    }
}

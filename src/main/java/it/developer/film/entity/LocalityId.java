package it.developer.film.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocalityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "nationality_name", nullable = false)
    private Nationality nationalityName;

    @Column(nullable = false)
    private String cityName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalityId that = (LocalityId) o;
        return Objects.equals(nationalityName, that.nationalityName) && Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalityName, cityName);
    }
}

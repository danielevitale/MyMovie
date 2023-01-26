package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;
    private LocalDate birthday;
    private String image;

    @ManyToOne
    @JoinColumn(name = "city_name", nullable = false)
    @JoinColumn(name = "nationality_name", nullable = false)
    private Locality locality;

    public Worker(String firstName, String lastName, LocalDate birthday, String image, Locality locality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.image = image;
        this.locality = locality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Id == worker.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}

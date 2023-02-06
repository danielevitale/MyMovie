package it.developer.film.payload.request;

import it.developer.film.entity.Nationality;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WorkerRequest {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String nationalityName;
    private String cityName;
}

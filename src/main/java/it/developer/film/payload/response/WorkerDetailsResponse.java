package it.developer.film.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class WorkerDetailsResponse {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String cityName;
    private String nationalityName;

    private String filename;

    private List<MovieWorkerResponse> movieList = new ArrayList<>();

    public WorkerDetailsResponse(long id, String firstName, String lastName, LocalDate birthday, String cityName, String nationalityName, String filename) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.cityName = cityName;
        this.nationalityName = nationalityName;
        this.filename = filename;
    }
}

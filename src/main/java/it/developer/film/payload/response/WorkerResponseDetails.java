package it.developer.film.payload.response;

import it.developer.film.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerResponseDetails {

    private long Id;
    private String firstName;
    private String lastName;
    private String image;
    private Role role;


}

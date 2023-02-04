package it.developer.film.payload.response;

import it.developer.film.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieWorkerResponse {

    private String movieTitle;

    private Role role;

}

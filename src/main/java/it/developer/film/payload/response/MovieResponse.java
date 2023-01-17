package it.developer.film.payload.response;

import it.developer.film.entity.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private String title;
    private String plot;
    private Long duration;

}

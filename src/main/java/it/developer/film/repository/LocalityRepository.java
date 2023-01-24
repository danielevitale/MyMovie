package it.developer.film.repository;


import it.developer.film.entity.Locality;
import it.developer.film.entity.LocalityId;
import it.developer.film.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, LocalityId> {

    @Query(value="SELECT * FROM locality WHERE nationality_name = :nationality AND city_name = :city ", nativeQuery = true)
    Locality findByLocality(@Param("nationality") String nationality, @Param("city") String city);

    /*
    // Metodo alternativo
    boolean existsByLocalityIdNationalityNameAndLocalityIdCityName(Nationality nationality, String city);
     */
}

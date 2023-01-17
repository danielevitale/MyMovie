package it.developer.film.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] AUTH_LIST = {

            "/movie/**",
            "/nationality/**",
    };


    /*

    POSTMAN -->(request)-->filtri -->interceptor -->CONTROLLER

    JwtTokenFilter intercetta la richiesta prima che entri nel sistema; al suo interno trova un JWT(Json Web Tojken);
    lo decodifica e dentro ci trova il nome utente e
    tutte le informazioni per capire se l'utente è autorizzato. Se è autorizzato passa la richiesta al controller,
    altrimenti la blocca.
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();


    }
}
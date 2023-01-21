package it.developer.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
public class FilmApplication {

	public static void main(String[] args) {
		System.out.println("BENVENUTI");
		SpringApplication.run(FilmApplication.class, args);
	}

}

package com.quantumtech.vido;

import com.quantumtech.vido.genre.Genre;
import com.quantumtech.vido.movie.Movie;
import com.quantumtech.vido.movie.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class VidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MovieRepository repository) {
		ArrayList<Genre> genres = new ArrayList<>();
		genres.add(new Genre(null,"Animation"));
		genres.add(new Genre(null,"Adventure"));
		genres.add(new Genre(null,"Comedy"));
		return args -> {
			repository.save(new Movie(null,
					"Hotel Transylvania: Transformania",
					"After one experiment, Johnny turns into a monster and everyone else becomes human. Now it has to be seen whether they will be able to reverse this experiment.",
					"2022",
					"1h 27m",
					"Eng", genres
					));
			};
	}
}

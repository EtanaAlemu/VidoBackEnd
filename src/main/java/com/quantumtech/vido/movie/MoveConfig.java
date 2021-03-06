package com.quantumtech.vido.movie;

import com.quantumtech.vido.genre.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MoveConfig {

    @Bean
    CommandLineRunner MoviesCommandLineRunner(MovieRepository repository){

        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(new Genre(3L,"Animation"));
        genres.add(new Genre(4L,"Adventure"));
        genres.add(new Genre(5L,"Comedy"));

        return args -> {
            Movie Dune = new Movie(
                    null,
                "Dune",
                "Feature adaptation of Frank Herbert's science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset and most vital element in the galaxy.",
                "2021",
                "2h 35m",
                "http://localhost:8080/api/v1/movies/image/server1.png",
                "English",
                    genres
            );

        Movie F9 = new Movie(
                null,
                "F9: The Fast Saga",
                "Dom and the crew must take on an international terrorist who turns out to be Dom and Mia's estranged brother.",
                "2021",
                "2h 23m",
                "http://localhost:8080/api/v1/movies/image/server1.png",
                "English",
                genres
        );

        repository.saveAll(List.of(Dune,F9));
        };
    }
}

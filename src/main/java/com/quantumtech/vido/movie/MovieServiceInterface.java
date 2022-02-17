package com.quantumtech.vido.movie;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface MovieServiceInterface {

    Movie createMovie(Movie movie, String filename);
    Collection<Movie> list(int limit);
    Movie getMovie(Long id);
    Movie getJson(String movie);
    Movie updateMovie(Movie movie);
    Boolean deleteMovie(Long id);

    void storeImage(MultipartFile file);
}

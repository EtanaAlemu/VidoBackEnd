package com.quantumtech.vido.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantumtech.vido.genre.Genre;
import com.quantumtech.vido.storage.StorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Consumer;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Slf4j
public class MovieService implements MovieServiceInterface{

    private final MovieRepository repository;
    private final Path rootLocation;

    @Autowired
    public MovieService(@Qualifier("MovieRepo") MovieRepository repository, StorageProperties properties) {
        this.repository = repository;
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public List<Movie> getMovies(){
        log.info("Fetching all movies" );
        return repository.findAll();
    }


    @Override
    public Movie createMovie(Movie movie, String fileName) {
        log.info("Creating movie " );
        Optional<Movie> movieOptional = repository.findMoviesByTitle(movie.getTitle());
        movie.setImageUrl("http://localhost:8080/api/v1/movies/image/"+fileName);
        if(movieOptional.isPresent()){
            throw new IllegalStateException("Title taken");
        }
        return repository.save(movie);
    }

    @Override
    public void storeImage(MultipartFile file) {

        log.info("Storing image "+file.getOriginalFilename() );
        try {
            if (file.isEmpty()) {
                throw new IllegalStateException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new IllegalStateException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to store file.", e);
        }
    }

    public Collection<Movie> list(int limit) {
        log.info("Fetching movies to " +limit+" page" );
        return repository.findAll(of(0, limit)).toList();
    }

    @Override
    public Movie getMovie(Long id) {
        log.info("Fetching movie by id: {}", id);
        return repository.findById(id).get();
    }

    @Override
    public Movie getJson(String movie) {

        Movie movieJson = new Movie();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            movieJson = objectMapper.readValue(movie, Movie.class);
        } catch (JsonProcessingException e) {
            log.info("Exception getJson: {}", e.getMessage());
        }
        return movieJson;
    }

    @Transactional
    @Override
    public Movie updateMovie(Movie movie) {
        Movie newMovie = repository.findById(movie.getId()).orElseThrow(() -> new IllegalStateException("Movie with id "+movie.getId()+" is not exists"));
        if ( movie.getTitle() != null && movie.getTitle().length() > 0 && !Objects.equals(newMovie.getTitle(), movie.getTitle()))
            newMovie.setTitle(movie.getTitle());
        if ( movie.getDescription() != null && movie.getDescription().length() > 0 && !Objects.equals(newMovie.getDescription(), movie.getDescription()))
            newMovie.setDescription(movie.getDescription());
        if ( movie.getYear() != null && movie.getYear().length() > 0 && !Objects.equals(newMovie.getYear(), movie.getYear()))
            newMovie.setYear(movie.getYear());
        if ( movie.getTime() != null && movie.getTime().length() > 0 && !Objects.equals(newMovie.getTime(), movie.getTime()))
            newMovie.setTime(movie.getTime());
        if ( movie.getLang() != null && movie.getLang().length() > 0 && !Objects.equals(newMovie.getLang(), movie.getLang()))
            newMovie.setLang(movie.getLang());

        log.info("Updating movie: {}", newMovie.getTitle());
        return repository.save(newMovie);
    }

    @Override
    public Boolean deleteMovie(Long id) {
        log.info("Deleting Movie by ID: {}", id);
        repository.deleteById(id);
        return TRUE;
    }

    private String setMovieImageUrl() {
        String[] imageNames = { "server1.png", "server2.png", "server3.png", "server4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }

}

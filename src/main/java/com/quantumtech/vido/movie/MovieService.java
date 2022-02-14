package com.quantumtech.vido.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieService(@Qualifier("MovieRepo") MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMovies(){
        return repository.findAll();
    }

    public Movie get(Long id) {
        return repository.findById(id).get();
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = repository.findMoviesByTitle(movie.getTitle());
        if(movieOptional.isPresent()){
            throw new IllegalStateException("Title taken");
        }
        repository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        boolean exist = repository.existsById(movieId);
        if(!exist){
            throw new IllegalStateException("Movie with id "+movieId+" is not exists");
        }

        repository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(Long movieId, String title, String description, String year, String time, String lang) {
        Movie movie = repository.findById(movieId).orElseThrow(() -> new IllegalStateException("Movie with id "+movieId+" is not exists"));
        if ( title != null && title.length() > 0 && !Objects.equals(movie.getTitle(), title))
            movie.setTitle(title);
        if ( description != null && description.length() > 0 && !Objects.equals(movie.getDescription(), description))
            movie.setDescription(description);
        if ( year != null && year.length() > 0 && !Objects.equals(movie.getYear(), year))
            movie.setYear(year);
        if ( time != null && time.length() > 0 && !Objects.equals(movie.getTime(), time))
            movie.setTime(time);
        if ( lang != null && lang.length() > 0 && !Objects.equals(movie.getLang(), lang))
            movie.setLang(lang);
    }

}

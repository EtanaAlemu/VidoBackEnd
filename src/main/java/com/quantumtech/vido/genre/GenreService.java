package com.quantumtech.vido.genre;

import com.quantumtech.vido.enumeration.GenreEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Slf4j
public class GenreService implements GenreServiceInterface{

    private final GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> getGenres() {
        return repository.findAll();
    }

//    public void addNewGenre(Genre genre) {
//
//        Optional<Genre> genreOptional = repository.findGenreByGenreType(genre.getGenreType());
//        if(genreOptional.isPresent()){
//            throw new IllegalStateException("Genre Type Already taken ");
//        }
//        repository.save(genre);
//    }

    @Override
    public Genre createGenre(Genre genre) {

        log.info("Creating movie " );
        Optional<Genre> genreOptional = repository.findGenreByGenreType(genre.getGenreType());
        if(genreOptional.isPresent()){
            throw new IllegalStateException("Genre Type Already taken ");
        }
       return repository.save(genre);
    }

    @Override
    public Collection<Genre> list() {
        log.info("Fetching all Genres" );
        return repository.findAll();
    }

    @Override
    public Genre getGenre(Long id) {
        log.info("Fetching genre by id: {}", id);
        return repository.findById(id).get();
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Genre newGenre= repository.findById(genre.getId()).orElseThrow(() -> new IllegalStateException("Genre with id "+genre.getId()+" is not exists"));
        if ( genre.getGenreType() != null && genre.getGenreType().length() > 0 && !Objects.equals(newGenre.getGenreType(), genre.getGenreType()))
            newGenre.setGenreType(genre.getGenreType());

        log.info("Updating Genre: {}", newGenre.getGenreType());
        return repository.save(newGenre);
    }

    @Override
    public Boolean deleteGenre(Long id) {
        log.info("Deleting genre by ID: {}", id);
        boolean exist = repository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Genre with id "+id+" is not exists");
        }

        repository.deleteById(id);
        return true;
    }

//    public void deleteGenre(Long genreID) {
//        boolean exist = repository.existsById(genreID);
//        if(!exist){
//            throw new IllegalStateException("Genre with id "+genreID+" is not exists");
//        }
//
//        repository.deleteById(genreID);
//
//    }

//    @Transactional
//    public void updateGenre(Long genreId, String name) {
//        Genre genre= repository.findById(genreId).orElseThrow(() -> new IllegalStateException("Genre with id "+genreId+" is not exists"));
//        if ( name != null && name.length() > 0 && !Objects.equals(genre.getGenreType(), name))
//            genre.setGenreType(name);
//    }
}

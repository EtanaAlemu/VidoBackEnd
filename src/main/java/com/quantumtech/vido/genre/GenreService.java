package com.quantumtech.vido.genre;

import com.quantumtech.vido.enumeration.GenreEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> getGenres() {
        return repository.findAll();
    }

    public void addNewGenre(Genre genre) {

        Optional<Genre> genreOptional = repository.findGenreByGenreType(genre.getGenreType());
        if(genreOptional.isPresent()){
            throw new IllegalStateException("Genre Type Already taken ");
        }
        repository.save(genre);
    }

    public void deleteGenre(Long genreID) {
        boolean exist = repository.existsById(genreID);
        if(!exist){
            throw new IllegalStateException("Genre with id "+genreID+" is not exists");
        }

        repository.deleteById(genreID);

    }

    @Transactional
    public void updateGenre(Long genreId, String name) {
        Genre genre= repository.findById(genreId).orElseThrow(() -> new IllegalStateException("Genre with id "+genreId+" is not exists"));
        if ( name != null && name.length() > 0 && !Objects.equals(genre.getGenreType(), name))
            genre.setGenreType(name);
    }
}

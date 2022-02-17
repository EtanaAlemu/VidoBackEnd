package com.quantumtech.vido.genre;


import java.util.Collection;

public interface GenreServiceInterface {

    Genre createGenre(Genre genre);
    Collection<Genre> list();
    Genre getGenre(Long id);
    Genre updateGenre(Genre genre);
    Boolean deleteGenre(Long id);
}

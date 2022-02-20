package com.quantumtech.vido.genre;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Collection;

@SecurityRequirement(name = "bearerAuth")
public interface GenreServiceInterface {

    Genre createGenre(Genre genre);
    Collection<Genre> list();
    Genre getGenre(Long id);
    Genre updateGenre(Genre genre);
    Boolean deleteGenre(Long id);
}

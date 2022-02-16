package com.quantumtech.vido.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long>
{

    @Query(value = "SELECT * FROM Genre g WHERE g.genre_type = 1",nativeQuery = true)
    Optional<Genre> findGenreByGenreType(String genreType);
}


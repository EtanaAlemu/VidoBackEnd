package com.quantumtech.vido.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository
//        extends JpaRepository<GenreEnum,Long>
{

//    @Query(value = "SELECT * FROM Genre g WHERE g.name = 1",nativeQuery = true)
//    Optional<GenreEnum> findGenreByName(String name);
}


package com.quantumtech.vido.movie;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Qualifier("MovieRepo")
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m from Movie m WHERE m.title = ?1")
    Optional<Movie> findMoviesByTitle(String title);
}

package com.quantumtech.vido.movie;

import com.quantumtech.vido.genre.Genre;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String year;
    private String time;
    private String imageUrl;
    private String lang;
    @ManyToMany
    @JoinTable(
            name = "is_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Collection<Genre> isGenre;
}

package com.quantumtech.vido.movie;

import com.quantumtech.vido.enumeration.GenreEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(
//            name = "movie_sequence",
//            sequenceName = "movie_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "movie_sequence"
//    )
    private Long id;
    private String title;
    private String description;
    private String year;
    private String time;
    private String lang;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GenreEnum", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<GenreEnum> genreEnum;

//    public Movie() {
//    }
//
//    public Movie(Long id, String title, String description, String year, String time, String lang) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.year = year;
//        this.time = time;
//        this.lang = lang;
//    }
//
//    public Movie(String title, String description, String year, String time, String lang) {
//        this.title = title;
//        this.description = description;
//        this.year = year;
//        this.time = time;
//        this.lang = lang;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getLang() {
//        return lang;
//    }
//
//    public void setLang(String lang) {
//        this.lang = lang;
//    }
//
//    @Override
//    public String toString() {
//        String s = "title='" + title + '\'';
//
//        return "Movie{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", year='" + year + '\'' +
//                ", time='" + time + '\'' +
//                ", lang='" + lang + '\'' +
//                '}';
//    }
}

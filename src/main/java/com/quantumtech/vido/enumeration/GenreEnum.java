package com.quantumtech.vido.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenreEnum {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    CYBERPUNK("Cyberpunk"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    FILM_NOIR("Film-Noir"),
    GAME_SHOW("Game-Show"),
    HISTORICAL("Historical"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    NEWS("News"),
    REALITY_TV("Reality-Tv"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi"),
    SATIRE("Satire"),
    SPECULATIVE("Speculative"),
    SPORT("Sport"),
    TALK_SHOW("Talk-Show"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private String description;

    private GenreEnum(String description) {
        this.description = description;
    }

    @JsonCreator
    public static GenreEnum create(String description) {
        GenreEnum[] types = GenreEnum.values();

        for (GenreEnum type : types) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }

        return null;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

}

package com.quantumtech.vido.security;

public enum ApplicationUserPermission {
    MOVIE_READ("movie:read"),
    MOVIE_WRITE("movie:write"),
    GENRE_READ("genre:read"),
    GENRE_WRITE("genre:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

package com.naumen.films.application.controller.service.genre_service;

import com.naumen.films.application.model.data.entities.Genre;

import java.util.HashSet;

public interface GenreService {

    public HashSet<Genre> getAll();

    public Genre getById(int id);

    public void postGenre(Genre genre);
}

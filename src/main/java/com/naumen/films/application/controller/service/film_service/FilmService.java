package com.naumen.films.application.controller.service.film_service;

import com.naumen.films.application.model.data.entities.Film;

public interface FilmService {

    public Iterable<Film> getAll();

    public Film getById(int id);

    public void postFilm(Film film);

    public void updateFilm(Film film);
}

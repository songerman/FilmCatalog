package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {

    public Film findById(int id);

    public void saveFilm(Film film);

    public void updateFilm(Film film);

    public void deleteFilm(Film film);
}
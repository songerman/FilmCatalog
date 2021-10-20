package com.naumen.films.application.controller.service.film_service;

import com.naumen.films.application.model.data.entities.Film;
import com.naumen.films.application.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Iterable<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film getById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    public void postFilm(Film film) {
        try {
            filmRepository.saveFilm(film);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateFilm(Film film) {
        filmRepository.updateFilm(film);
    }
}

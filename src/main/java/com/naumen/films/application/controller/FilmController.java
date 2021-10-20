package com.naumen.films.application.controller;

import com.naumen.films.application.controller.service.film_service.FilmService;
import com.naumen.films.application.model.data.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/film_api/v1/films", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/get_all")
    public List<Film> getAllFilms() {
        List<Film> list = (List<Film>) filmService.getAll();
        return list;
    }

    @GetMapping("/get_by_id/{id}")
    public Film getFilmById(@PathVariable int id) {
        return filmService.getById(id);
    }

    @RequestMapping(
            value = "/add_film",
            produces = "application/json",
            method = RequestMethod.POST
    )
    public void addFilm(@RequestBody Film film) {
        filmService.postFilm(film);
    }

    @RequestMapping(
            value = "/update_film",
            produces = "application/json",
            method = RequestMethod.PUT
    )
    public void updateFilm(@PathVariable int id, @RequestBody Film film) {
        Film targetFilm = filmService.getById(id);
        targetFilm.setGenres(film.getGenres());
        targetFilm.setTitle(film.getTitle());
        targetFilm.setRating(film.getRating());
        targetFilm.setReviews(film.getReviews());
        targetFilm.setLikedBy(film.getLikedBy());
        filmService.updateFilm(targetFilm);
    }
}

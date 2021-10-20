package com.naumen.films.application.controller;

import com.naumen.films.application.controller.service.genre_service.GenreService;
import com.naumen.films.application.model.data.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping(value = "/film_api/v1/genres", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/get_all")
    public HashSet<Genre> getAllGenres() {
        HashSet<Genre> iterable = genreService.getAll();
        return iterable;
    }

    @GetMapping("/get_by_id/{id}")
    public Genre getById(@PathVariable int id) {
        return genreService.getById(id);
    }

    @RequestMapping(
            value = "/add_genre",
            produces = "application/json",
            method = RequestMethod.POST
    )
    public void addGenre(@RequestBody Genre genre) {
        genreService.postGenre(genre);
    }
}

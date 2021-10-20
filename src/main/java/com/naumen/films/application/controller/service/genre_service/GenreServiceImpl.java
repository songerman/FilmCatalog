package com.naumen.films.application.controller.service.genre_service;

import com.naumen.films.application.model.data.entities.Genre;
import com.naumen.films.application.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public HashSet<Genre> getAll() {
        return new HashSet<>(
                (Collection<? extends Genre>) genreRepository.findAll()
        );
    }

    @Override
    public Genre getById(int id) {
        return genreRepository.findById(id);
    }

    @Override
    public void postGenre(Genre genre) {
        try {
            genreRepository.saveGenre(genre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

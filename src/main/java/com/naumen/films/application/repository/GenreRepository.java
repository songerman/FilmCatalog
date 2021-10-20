package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

    public Genre findById(int id);

    public void saveGenre(Genre genre);

    public void updateGenre(Genre genre);

    public void deleteGenre(Genre genre);
}

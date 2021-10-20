package com.naumen.films.application.model.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genres")
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "genre_name")
    private String genreName;

    @JsonBackReference
    @ManyToMany(mappedBy = "genres")
    private List<Film> films;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(genreName, genre.genreName) && Objects.equals(films, genre.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreName, films);
    }
}

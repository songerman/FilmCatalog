package com.naumen.films.application.model.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {

    @Column(name = "filmid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;

    @Column
    private String title;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "film_genres",
            joinColumns = @JoinColumn(name = "filmid"),
            inverseJoinColumns = @JoinColumn(name = "genreid")
    )
    private Set<Genre> genres;

    @Column
    private Float rating;

    @Column
    private String poster;

    @Column
    private String overview;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @JsonIgnore
    @ManyToMany(mappedBy = "favourites")
    private Set<User> likedBy;

    public void addReview(Review review) {
        review.setFilm(this);

        getReviews().add(review);
    }

    public void removeReview(Review review) {
        getReviews().remove(review);
    }
}
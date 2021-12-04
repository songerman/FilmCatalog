package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "insert into users_film (userid, favourite) values (:userId, :filmId)", nativeQuery = true)
    void addFavourite(int userId, int filmId);
}

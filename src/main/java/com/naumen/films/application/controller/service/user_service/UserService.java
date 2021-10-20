package com.naumen.films.application.controller.service.user_service;

import com.naumen.films.application.model.data.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(int id);

    void delete(int id);
}

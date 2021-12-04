package com.naumen.films.application.controller;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.naumen.films.application.controller.service.user_service.UserService;
import com.naumen.films.application.dto.AddFavouriteRequestDto;
import com.naumen.films.application.model.data.entities.Film;
import com.naumen.films.application.model.data.entities.User;
import com.naumen.films.application.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/film_api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {

        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(
            value = "/add_favourite",
            produces = "application/json",
            method = RequestMethod.POST
    )
    public void addFavourite(@RequestBody AddFavouriteRequestDto addFavouriteRequest) {

        String username = jwtTokenProvider.getUsername(addFavouriteRequest.getToken());
        User user = userService.findByUsername(username);
        userService.addFavourite(user.getId(), addFavouriteRequest.getFilmId());
    }

    @GetMapping("/get_favourites")
    public Set<Film> getFavourites(@RequestHeader("token") String token) {

        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);

        return user.getFavourites();
    }
}
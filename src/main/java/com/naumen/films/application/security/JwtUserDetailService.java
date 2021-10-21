package com.naumen.films.application.security;

import com.naumen.films.application.controller.service.user_service.UserService;
import com.naumen.films.application.model.data.entities.User;
import com.naumen.films.application.security.jwt.JwtUser;
import com.naumen.films.application.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + "not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}

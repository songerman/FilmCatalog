package com.naumen.films.application.controller;

import com.naumen.films.application.controller.service.user_service.UserService;
import com.naumen.films.application.dto.AuthenticationRequestDto;
import com.naumen.films.application.dto.RegistrationRequestDto;
import com.naumen.films.application.model.data.entities.Role;
import com.naumen.films.application.model.data.entities.User;
import com.naumen.films.application.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.naumen.films.application.utils.Constants.USER_ROLE;

@RestController
@RequestMapping(value = "/film_api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserService userService,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + "not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            System.out.println("Something went wrong!\n" + e);
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        try {
            String email = registrationRequestDto.getEmail();
            String username = registrationRequestDto.getUsername();
            String password_1 = registrationRequestDto.getPassword_1();
            String password_2 = registrationRequestDto.getPassword_2();

            if (!password_1.equals(password_2)) {
                throw new BadCredentialsException("Password mismatch");
            }

            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password_1);
            user.setEnabled(true);

            userService.register(user);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            System.out.println("Something went wrong!\n" + e);
            throw e;
        }
    }
}

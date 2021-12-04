package com.naumen.films.application.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {

    private String email;
    private String username;
    private String password_1;
    private String password_2;
}

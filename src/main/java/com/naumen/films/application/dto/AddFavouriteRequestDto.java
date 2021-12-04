package com.naumen.films.application.dto;

import lombok.Data;

@Data
public class AddFavouriteRequestDto {

    private String token;
    private int filmId;
}

package com.hudilong.advertassignment.web.dtos;

public record ErrorResponseDto(int status,
        String error,
        String message) {

}

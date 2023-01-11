package ru.smn.fantasyteam.dto.user;

public record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {
}

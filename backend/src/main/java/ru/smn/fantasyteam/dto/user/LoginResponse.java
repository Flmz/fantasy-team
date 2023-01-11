package ru.smn.fantasyteam.dto.user;

public record LoginResponse(String access_jwt_token,
                            String refresh_jwt_token) {
}

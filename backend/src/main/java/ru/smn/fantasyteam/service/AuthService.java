package ru.smn.fantasyteam.service;

import ru.smn.fantasyteam.dto.user.LoginRequest;
import ru.smn.fantasyteam.dto.user.LoginResponse;
import ru.smn.fantasyteam.dto.user.RefreshTokenResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    RefreshTokenResponse refreshToken(String refreshToken);
}

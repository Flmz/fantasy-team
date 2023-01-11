package ru.smn.fantasyteam.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import ru.smn.fantasyteam.dto.user.LoginRequest;
import ru.smn.fantasyteam.dto.user.LoginResponse;
import ru.smn.fantasyteam.dto.user.RefreshTokenResponse;
import ru.smn.fantasyteam.exceptions.user.LoginOrPasswordException;
import ru.smn.fantasyteam.repository.UserRepository;
import ru.smn.fantasyteam.security.JpaUserDetailsService;
import ru.smn.fantasyteam.security.SecurityUser;
import ru.smn.fantasyteam.security.TokenService;
import ru.smn.fantasyteam.service.AuthService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authManager;
    private final JpaUserDetailsService userDetailsService;

    public LoginResponse login(LoginRequest loginRequest) {
        String userLogin = loginRequest.login();
        String userPassword = loginRequest.password();
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin,
                    userPassword));
            System.out.println("asd");
            SecurityUser user = (SecurityUser) authenticate.getPrincipal();
            String access_token = tokenService.generateAccessToken(user);
            String refresh_token = tokenService.generateRefreshToken(user);
            return new LoginResponse(access_token, refresh_token);
        } catch (AuthenticationException exc) {
            throw new LoginOrPasswordException();
        }
    }

    public RefreshTokenResponse refreshToken(String refreshToken) {
        String email = tokenService.parseToken(refreshToken);
        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(email);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);
        return new RefreshTokenResponse(access_token, refresh_token);
    }
}

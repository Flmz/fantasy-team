package ru.smn.fantasyteam.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smn.fantasyteam.dto.ErrorResponse;
import ru.smn.fantasyteam.dto.user.LoginRequest;
import ru.smn.fantasyteam.dto.user.LoginResponse;
import ru.smn.fantasyteam.dto.user.RefreshTokenResponse;
import ru.smn.fantasyteam.dto.user.UserRegister;
import ru.smn.fantasyteam.exceptions.user.UserParamsNotCorrectException;
import ru.smn.fantasyteam.service.AuthService;
import ru.smn.fantasyteam.service.RegisterService;
import ru.smn.fantasyteam.validator.UserValidator;

import static ru.smn.fantasyteam.util.Utils.writeErrorMessage;

@Validated
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final RegisterService registerService;
    private final UserValidator validator;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/token/refresh")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenResponse refreshToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String refreshToken = headerAuth.substring(7);
        return authService.refreshToken(refreshToken);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegister userToRegister, BindingResult bindingResult) {
        validator.validate(userToRegister, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new UserParamsNotCorrectException(writeErrorMessage(bindingResult));
        }

        registerService.register(userToRegister, bindingResult);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(UserParamsNotCorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserParamsNotCorrect(UserParamsNotCorrectException exc) {
        return new ErrorResponse(exc.getMessage(), 400);
    }
}

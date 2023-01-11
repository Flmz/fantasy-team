package ru.smn.fantasyteam.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotBlank @NotNull String login, @NotBlank @NotNull String password) {
}


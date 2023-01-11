package ru.smn.fantasyteam.exceptions.user;

public class UserParamsNotCorrectException extends RuntimeException {
    public UserParamsNotCorrectException(String message) {
        super(message);
    }
}

package ru.smn.fantasyteam.exceptions.user;

public class LoginOrPasswordException extends RuntimeException {
    public LoginOrPasswordException() {
        super("Login or password incorrect");
    }
}

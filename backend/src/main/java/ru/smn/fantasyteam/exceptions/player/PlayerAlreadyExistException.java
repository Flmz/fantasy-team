package ru.smn.fantasyteam.exceptions.player;

public class PlayerAlreadyExistException extends RuntimeException {
    public PlayerAlreadyExistException(String nickname) {
        super(String.format("We are already have player with nickname %s", nickname));
    }
}

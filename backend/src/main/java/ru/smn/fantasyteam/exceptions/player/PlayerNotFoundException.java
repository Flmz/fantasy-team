package ru.smn.fantasyteam.exceptions.player;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String nickname) {
        super(String.format("Player with such nickname { %s } not found", nickname));
    }
}

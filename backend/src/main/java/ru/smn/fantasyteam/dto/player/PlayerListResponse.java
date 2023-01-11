package ru.smn.fantasyteam.dto.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PlayerListResponse {
    private final List<PlayerDTO> playerDTOS;
}

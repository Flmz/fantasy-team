package ru.smn.fantasyteam.strategy.player;

import ru.smn.fantasyteam.dto.player.PlayerListResponse;

import java.util.List;

public interface PlayerFinderStrategy {
    List<PlayerListResponse> find(String sortBy);

    PlayerFinderType getStrategyType();
}

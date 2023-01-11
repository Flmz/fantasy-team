package ru.smn.fantasyteam.strategy.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlayerFinderFactory {
    private final Map<PlayerFinderType, PlayerFinderStrategy> strategies;

    @Autowired
    public PlayerFinderFactory(Set<PlayerFinderStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(
                        PlayerFinderStrategy::getStrategyType,
                        finder -> finder
                ));
    }

    public PlayerFinderStrategy getStrategy(PlayerFinderType strategyType) {
        return strategies.get(strategyType);
    }
}

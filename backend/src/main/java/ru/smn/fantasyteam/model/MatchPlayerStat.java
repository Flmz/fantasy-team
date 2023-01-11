package ru.smn.fantasyteam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_player_stat")
public class MatchPlayerStat {
    @EmbeddedId
    private MatchPlayerStatId id = new MatchPlayerStatId();

    @ManyToOne
    @MapsId("playerId")
    private Player player;

    @ManyToOne
    @MapsId("matchId")
    private Match match;

    @Embedded
    private PlayerStatistic playerStatistic;
}

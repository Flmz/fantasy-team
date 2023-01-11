package ru.smn.fantasyteam.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class MatchPlayerStatId extends MatchPlayerStat implements Serializable {
    private Long playerId;

    private Long mapId;

}

package ru.smn.fantasyteam.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlayerStatistic {
    private int frags;

    private int death;

    private int assist;

    private int flash_assist;

    private double adr;

    private double rating;

    private double duelsWin;

    private int kdDiff;
}

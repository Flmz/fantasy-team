package ru.smn.fantasyteam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MATCH")
public class Match {

    @Column
    @ManyToMany(targetEntity = Map.class)
    @JoinTable(name = "match_map",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "map_id"))
    Set<Map> mapsInMatch = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Team firstTeam;

    @ManyToOne
    private Team secondTeam;

    @ManyToOne
    private Team winner;

    @ManyToOne
    private Tournament tournament;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private Set<MatchPlayerStat> matchPlayerStats = new HashSet<>();

    private boolean is_started;

    private boolean isEnded;

    private String score;

    public void setWinner(Team team) {
        if (!team.equals(this.firstTeam) || !team.equals(this.secondTeam)) {
            throw new RuntimeException(); //TODO
        }
    }
}

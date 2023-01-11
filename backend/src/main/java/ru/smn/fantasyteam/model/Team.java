package ru.smn.fantasyteam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TEAM")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> roster = new ArrayList<>();

    public void addPlayerToRoster(Player player) {
        this.roster.add(player);
        player.setTeam(this);
    }

    public void removePlayerFromRoster(Player player) {
        this.roster.remove(player);
        player.setTeam(null);
    }


    public void setWinner(Team team) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

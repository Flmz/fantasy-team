package ru.smn.fantasyteam.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PLAYER")
public class Player {
    public long matchesPlayed;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String firstName;
    private String secondName;
    @ManyToOne
    private Team team;
    @Enumerated(EnumType.STRING)
    private GameRole role;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<MatchPlayerStat> playerStatInMatch = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(nickname, player.nickname) && Objects.equals(firstName, player.firstName) && Objects.equals(secondName, player.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, firstName, secondName);
    }

    public enum GameRole {
        SUPPORT,
        ENTRY,
        AWPER,
        CAPTAIN,
        LURK,
    }
}

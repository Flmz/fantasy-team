package ru.smn.fantasyteam.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String secondName;

    private String nickName;

    private String email;

    private String password;

    private String avatar;

    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team favouriteTeam;

    private boolean isActive;

    @Builder.Default
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
}

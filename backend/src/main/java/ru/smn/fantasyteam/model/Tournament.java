package ru.smn.fantasyteam.model;

import jakarta.persistence.*;

@Table(name = "tournament")
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}

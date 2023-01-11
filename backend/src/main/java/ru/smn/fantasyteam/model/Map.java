package ru.smn.fantasyteam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "map")
public class Map {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MapType map;

    public enum MapType {
        DE_MIRAGE, DE_ANUBIS, DE_VERTIGO, DE_INFERNO, DE_ANCIENT, DE_NUKE, DE_OVERPASS
    }
}

package com.tbh.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "challenges")
@Getter
@Setter
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String difficulty;
    private int solves;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private int points;
    @OneToOne
    Category category;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instance> instances;
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions;


    public Challenge(Long id, String name, String description, String difficulty, int solves, Date createdAt, int points, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.solves = solves;
        this.createdAt = createdAt;
        this.points=points;
        this.category=category;
    }

    public Challenge() {

    }


}


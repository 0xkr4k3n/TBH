package com.tbh.backend.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "challenges")
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

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instance> instances;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions;


    public Challenge(Long id, String name, String description, String difficulty, int solves, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.solves = solves;
        this.createdAt = createdAt;
    }

    public Challenge() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setSolves(int solves) {
        this.solves = solves;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getSolves() {
        return solves;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}


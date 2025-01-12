package com.tbh.backend.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String country;
    private int points;
    private int solves;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSolve;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instance> instances;


    public User(Long id, String username, String country, int points, int solves, Date lastSolve, Date createdAt) {
        this.id = id;
        this.username = username;
        this.country = country;
        this.points = points;
        this.solves = solves;
        this.lastSolve = lastSolve;
        this.createdAt = createdAt;
    }

    public User() {

    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSolves(int solves) {
        this.solves = solves;
    }

    public void setLastSolve(Date lastSolve) {
        this.lastSolve = lastSolve;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCountry() {
        return country;
    }

    public int getPoints() {
        return points;
    }

    public int getSolves() {
        return solves;
    }

    public Date getLastSolve() {
        return lastSolve;
    }
}

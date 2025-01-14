package com.tbh.backend.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Date;


@Entity
@Table(name = "users")
@Getter
@Setter
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

}

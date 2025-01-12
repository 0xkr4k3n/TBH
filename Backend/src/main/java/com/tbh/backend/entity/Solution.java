package com.tbh.backend.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "solutions")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Temporal(TemporalType.TIMESTAMP)
    private Date solvedAt;


    public Solution() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setSolvedAt(Date solvedAt) {
        this.solvedAt = solvedAt;
    }

    public Solution(Long id, User user, Challenge challenge, Date solvedAt) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.solvedAt = solvedAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Date getSolvedAt() {
        return solvedAt;
    }
}
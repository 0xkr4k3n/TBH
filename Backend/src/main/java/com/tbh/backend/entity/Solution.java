package com.tbh.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "solutions")
@Getter
@Setter
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


    public Solution(Long id, User user, Challenge challenge, Date solvedAt) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.solvedAt = solvedAt;
    }


}
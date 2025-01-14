package com.tbh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;

    private String country;
    private int points;
    private int solves;
    private Date lastSolve;
    private Date createdAt;

    public UserDTO(Long id, String username, String country, int points, int solves, Date lastSolve, Date createdAt) {
        this.id = id;
        this.username = username;
        this.country = country;
        this.points = points;
        this.solves = solves;
        this.lastSolve = lastSolve;
        this.createdAt = createdAt;
    }

    public UserDTO() {
    }

}

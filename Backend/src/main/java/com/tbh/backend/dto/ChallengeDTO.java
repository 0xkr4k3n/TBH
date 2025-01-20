package com.tbh.backend.dto;

import com.tbh.backend.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChallengeDTO {
    private Long id;
    private String name;
    private String description;
    private String difficulty;
    private int solves;
    private Date createdAt;
    private String category;
    private int points;
    private String path;


    public ChallengeDTO(Long id, String name, String description, String difficulty, int solves, Date createdAt, int points, String s, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.solves = solves;
        this.createdAt = createdAt;
        this.points=points;
        this.category=s;
        this.path=path;
    }


}

package com.tbh.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SolutionDTO {
    private Long id;
    private Long userId;
    private String username; 
    private Long challengeId;
    private String challengeName; 
    private Date solvedAt;

    
    public SolutionDTO() {}

    public SolutionDTO(Long id, Long userId, String username, Long challengeId, String challengeName, Date solvedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.solvedAt = solvedAt;
    }


}

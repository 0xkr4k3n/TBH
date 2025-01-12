package com.tbh.backend.dto;

import java.util.Date;

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

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public Date getSolvedAt() {
        return solvedAt;
    }

    public void setSolvedAt(Date solvedAt) {
        this.solvedAt = solvedAt;
    }
}

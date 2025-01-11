package com.tbh.backend.dto;

import java.sql.Date;

public class UserDTO {
    private Long id;
    private String username;
    private Date createdAt;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, Date createdAt) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
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
}

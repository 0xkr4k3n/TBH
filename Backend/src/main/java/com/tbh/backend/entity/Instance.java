package com.tbh.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instances")
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;
    private int port;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    public Instance(Long id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public Instance() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}

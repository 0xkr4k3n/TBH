package com.tbh.backend.dto;

public class IntanceDTO {
    private Long id;
    private String ip;
    private int port;

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

    public IntanceDTO(Long id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

}

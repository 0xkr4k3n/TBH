package com.tbh.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntanceDTO {
    private Long id;
    private String ip;
    private int port;


    public IntanceDTO(Long id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

}

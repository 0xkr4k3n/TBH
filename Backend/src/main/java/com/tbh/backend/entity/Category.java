package com.tbh.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Challenge> challenges;

    public Category() {
    }

    public Category(Long id, String name, List<Challenge> challenges) {
        this.id = id;
        this.name = name;
        this.challenges = challenges;
    }
}

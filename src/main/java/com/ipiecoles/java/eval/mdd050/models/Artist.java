package com.ipiecoles.java.eval.mdd050.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Artist")
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


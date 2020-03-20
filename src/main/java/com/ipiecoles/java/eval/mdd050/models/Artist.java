package com.ipiecoles.java.eval.mdd050.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Artist")
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    public Artist() {
    }

    public Artist(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}


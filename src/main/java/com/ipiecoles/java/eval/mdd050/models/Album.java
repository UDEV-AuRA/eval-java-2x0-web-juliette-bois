package com.ipiecoles.java.eval.mdd050.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    private Long artistId;

    public Album() {
    }

    public Album(String title, Long artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
}


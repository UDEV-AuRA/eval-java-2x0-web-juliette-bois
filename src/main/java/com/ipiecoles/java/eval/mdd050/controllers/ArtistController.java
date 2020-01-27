package com.ipiecoles.java.eval.mdd050.controllers;

import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    // Question 1 : Afficher un artiste
    @GetMapping(value = "/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable(value = "id") Long id) {
        try {
            Artist artist = artistRepository.findById(id).get();
            return ResponseEntity.ok(artist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

package com.ipiecoles.java.eval.mdd050.controllers;

import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // Question 2 : Recherche par nom
    @GetMapping(value = "", params = "name")
    public List<Artist> getArtistbyName(@RequestParam(value = "name") String name) {
        return artistRepository.findAllByName(name);
    }
}

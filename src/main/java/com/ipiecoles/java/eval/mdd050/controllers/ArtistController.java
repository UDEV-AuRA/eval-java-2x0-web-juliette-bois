package com.ipiecoles.java.eval.mdd050.controllers;

import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.repositories.ArtistRepository;
import com.ipiecoles.java.eval.mdd050.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;
    private ArtistService artistService;

    // Question 1 : Afficher un artiste
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Artist getArtistById(@PathVariable(value = "id") Long id) {
        return artistService.findById(id);
        /*try {
            Artist artist = artistRepository.findById(id).get();
            return ResponseEntity.ok(artist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }*/
    }

    // Question 2 : Recherche par nom
    @GetMapping(
            value = "",
            params = "name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Artist> getArtistbyName(@RequestParam(value = "name") String name) {
        return artistRepository.findAllByName(name);
    }

    // Question 3 : Liste des artistes
    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Page<Artist> getArtistSortBy(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortProperty") String sortProperty,
            @RequestParam(value = "sortDirection") String sortDirection
    ) {
        PageRequest pageRequest= PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    // Question 4 : Création d'un artiste
    @PostMapping(value = "")
    public ResponseEntity<Artist> createArtist(@RequestBody Artist newArtist) {
        return ResponseEntity.ok(artistRepository.save(newArtist));
    }

    // Question 5 : Modification d'un artiste

    // Question 6 : Suppression d'un artiste

}

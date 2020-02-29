package com.ipiecoles.java.eval.mdd050.controllers;

import com.ipiecoles.java.eval.mdd050.exceptions.ConflictException;
import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    // Question 1 : Afficher un artiste
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Artist getArtistById(@PathVariable(value = "id") Long id) throws ConflictException {

        return artistService.findById(id);
    }

    // Question 2 : Recherche par nom
    @GetMapping(
            value = "",
            params = "name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Artist> getArtistbyName(@RequestParam(value = "name") String name) throws ConflictException {
        return artistService.findByName(name);
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
    ) throws ConflictException {
        return artistService.findAllArtists(page, size, sortProperty, sortDirection);
    }

    // Question 4 : Création d'un artiste
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Artist createArtist(@RequestBody Artist newArtist) throws ConflictException {
        return artistService.createArtist(newArtist);
    }

    // Question 5 : Modification d'un artiste
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(@PathVariable("id") Long id, @RequestBody Artist artist){
        return artistService.updateArtist(artist, id);
    }

    // Question 6 : Suppression d'un artiste
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Long id) throws ConflictException {
        artistService.deleteArtist(id);
    }
}

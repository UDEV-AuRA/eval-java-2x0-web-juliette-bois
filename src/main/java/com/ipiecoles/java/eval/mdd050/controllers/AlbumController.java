package com.ipiecoles.java.eval.mdd050.controllers;

import com.ipiecoles.java.eval.mdd050.exceptions.ConflictException;
import com.ipiecoles.java.eval.mdd050.models.Album;
import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    // Question 7 : Ajout d'un album
    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Album createAlbum(@RequestBody Album newAlbum) throws ConflictException {
        return albumService.createAlbum(newAlbum);
    }

    // Question 8 : Suppression d'un album
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") Long id) throws ConflictException {
        this.albumService.deleteAlbum(id);
    }
}

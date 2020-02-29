package com.ipiecoles.java.eval.mdd050.services;

import com.ipiecoles.java.eval.mdd050.exceptions.ConflictException;
import com.ipiecoles.java.eval.mdd050.models.Album;
import com.ipiecoles.java.eval.mdd050.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album createAlbum(Album album) throws ConflictException {
        if (albumRepository.findByTitle(album.getTitle()) != null) {
            throw new ConflictException("L'album " + album.getTitle() + " existe déjà.");
        }

        return albumRepository.save(album);
    }

    public void deleteAlbum(Long idAlbum) throws ConflictException {
        if (!albumRepository.existsById(idAlbum)) {
            throw new ConflictException("L'album d'identifiant " + idAlbum + " n'existe pas !");
        }

        albumRepository.deleteById(idAlbum);
    }
}

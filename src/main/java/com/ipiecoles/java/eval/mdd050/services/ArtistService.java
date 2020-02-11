package com.ipiecoles.java.eval.mdd050.services;

import com.ipiecoles.java.eval.mdd050.exceptions.ConflictException;
import com.ipiecoles.java.eval.mdd050.models.Artist;
import com.ipiecoles.java.eval.mdd050.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArtistService {
    public static final int PAGE_SIZE_MIN = 10;
    public static final int PAGE_SIZE_MAX = 100;
    public static final int PAGE_MIN = 0;
    private static final String PAGE_VALID_MESSAGE = "La taille de la page doit être comprise entre 10 et 100";

    @Autowired
    private ArtistRepository artistRepository;

    public Artist createArtist(Artist artist) throws ConflictException {
        if (artistRepository.findByName(artist.getName()) != null) {
            throw new ConflictException("L'artiste " + artist.getName() + " existe déjà.");
        }

        return artistRepository.save(artist);
    }

    public Artist updateArtist(Artist artist, Long id) throws EntityNotFoundException, IllegalArgumentException {
        if (!artistRepository.existsById(id)) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas !");
        }

        if (!id.equals(artist.getId())) {
            throw new IllegalArgumentException("Requête invalide");
        }

        return artistRepository.save(artist);
    }

    public void deleteArtist(Long idArtist) throws EntityNotFoundException {
        if (!artistRepository.existsById(idArtist)) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + idArtist + " n'existe pas !");
        }

        artistRepository.deleteById(idArtist);
    }

    public Page<Artist> findAllArtists(
            @Min(message = "Le numéro de page ne peut être inférieur à 0", value = PAGE_MIN)
                    Integer page,
            @Min(value = PAGE_SIZE_MIN, message = PAGE_VALID_MESSAGE)
            @Max(value = PAGE_SIZE_MAX, message = PAGE_VALID_MESSAGE)
                    Integer size,
            String sortProperty,
            Sort.Direction sortDirection
    ) throws IllegalArgumentException {
        if (Arrays.stream(Artist.class.getDeclaredFields()).
                map(Field::getName).
                filter(s -> s.equals(sortProperty)).count() != 1) {
            throw new IllegalArgumentException("La propriété " + sortProperty + " n'existe pas !");
        }
        ;

        Pageable pageable = PageRequest.of(page, size, sortDirection, sortProperty);
        Page<Artist> artists = artistRepository.findAll(pageable);
        if (page >= artists.getTotalPages()) {
            throw new IllegalArgumentException("Le numéro de page ne peut être supérieur à " + artists.getTotalPages());
        } else if (artists.getTotalElements() == 0) {
            throw new EntityNotFoundException("Il n'y a aucun artiste dans la base de données");
        }
        return artists;
    }

    public Artist findById(Long id) throws EntityNotFoundException {
        Optional<Artist> artist = artistRepository.findById(id);
        if (!artist.isPresent()) {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé.");
        }
        return artist.get();
    }

    public Artist findByName(String name) throws EntityNotFoundException {
        List<Artist> artist = this.artistRepository.findAllByName(name);
        if (artist == null) {
            throw new EntityNotFoundException("L'artiste " + name + " n'a pas été trouvé.");
        }
        return (Artist) artist;
    }
}
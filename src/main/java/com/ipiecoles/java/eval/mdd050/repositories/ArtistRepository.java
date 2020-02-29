package com.ipiecoles.java.eval.mdd050.repositories;

import com.ipiecoles.java.eval.mdd050.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query(value = "select * from Artist where Artist.name like %:name%", nativeQuery = true)
    List<Artist> findAllByName(String name);

    Artist findByName(String name);
}

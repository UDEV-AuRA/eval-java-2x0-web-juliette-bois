package com.ipiecoles.java.eval.mdd050.repositories;

import com.ipiecoles.java.eval.mdd050.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}

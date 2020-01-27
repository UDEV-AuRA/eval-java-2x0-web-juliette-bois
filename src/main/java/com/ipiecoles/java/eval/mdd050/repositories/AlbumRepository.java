package com.ipiecoles.java.eval.mdd050.repositories;

import com.ipiecoles.java.eval.mdd050.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}

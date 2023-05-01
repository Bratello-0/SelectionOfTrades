package org.example.selectionOfTrades.repositories.skinCSGO.attributes;

import org.example.selectionOfTrades.models.skinCSGO.attributes.Quality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByQuality(String quality);
    Boolean existsByQuality(String quality);
}

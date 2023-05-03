package org.example.selectionOfTrades.repositories.attributes;

import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByQuality(String quality);
    Boolean existsByQuality(String quality);
}

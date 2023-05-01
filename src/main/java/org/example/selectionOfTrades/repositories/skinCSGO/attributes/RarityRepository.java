package org.example.selectionOfTrades.repositories.skinCSGO.attributes;

import org.example.selectionOfTrades.models.skinCSGO.attributes.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RarityRepository extends JpaRepository<Rarity, Long> {
    List<Rarity> findByRarity(String rarity);
    Boolean existsByRarity(String rarity);
}

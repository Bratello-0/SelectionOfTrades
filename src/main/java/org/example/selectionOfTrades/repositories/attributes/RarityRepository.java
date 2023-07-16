package org.example.selectionOfTrades.repositories.attributes;

import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.attributes.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RarityRepository extends JpaRepository<Rarity, Long> {
    List<Rarity> findByRarity(String rarity);
    Rarity findByTag(String tag);
}

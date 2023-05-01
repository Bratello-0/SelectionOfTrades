package org.example.selectionOfTrades.repositories.gunSkinCSGO;

import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    List<Skin> findByPrice(Double price);
}

package org.example.selectionOfTrades.repositories;

import org.example.selectionOfTrades.models.skinCSGO.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    List<Skin> findByPrice(Double price);
}

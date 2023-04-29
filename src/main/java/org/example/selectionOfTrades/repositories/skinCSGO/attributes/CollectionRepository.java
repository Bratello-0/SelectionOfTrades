package org.example.selectionOfTrades.repositories.skinCSGO.attributes;

import org.example.selectionOfTrades.models.skinCSGO.attributes.Collection;
import org.example.selectionOfTrades.models.skinCSGO.attributes.DataWeapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findByCaseName(String caseName);
}

package org.example.selectionOfTrades.repositories.attributes;

import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findByCaseName(String caseName);
    Collection findByTag(String tag);
}

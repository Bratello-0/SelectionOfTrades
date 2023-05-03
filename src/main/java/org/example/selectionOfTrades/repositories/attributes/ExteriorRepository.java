package org.example.selectionOfTrades.repositories.attributes;

import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExteriorRepository extends JpaRepository<Exterior, Long> {
    List<Exterior> findByExterior(String exterior);
    Boolean existsByExterior(String exterior);

}

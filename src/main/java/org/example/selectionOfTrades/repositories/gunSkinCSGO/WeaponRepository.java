package org.example.selectionOfTrades.repositories.gunSkinCSGO;

import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    List<Weapon> findByName(String name);
    boolean existsByName(String name);
}

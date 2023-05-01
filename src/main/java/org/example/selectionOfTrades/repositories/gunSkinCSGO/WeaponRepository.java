package org.example.selectionOfTrades.repositories.gunSkinCSGO;

import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}

package org.example.selectionOfTrades.repositories;

import org.example.selectionOfTrades.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    List<Weapon> findByName(String name);
}

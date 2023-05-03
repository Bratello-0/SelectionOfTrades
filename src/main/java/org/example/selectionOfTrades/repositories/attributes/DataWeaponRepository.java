package org.example.selectionOfTrades.repositories.attributes;

import org.example.selectionOfTrades.models.entities.attributes.DataWeapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataWeaponRepository extends JpaRepository<DataWeapon, Long> {

    List<DataWeapon> findByWeaponName(String weaponName);
    Boolean existsByWeaponName(String weaponName);
}

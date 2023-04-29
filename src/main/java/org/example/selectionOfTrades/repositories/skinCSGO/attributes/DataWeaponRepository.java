package org.example.selectionOfTrades.repositories.skinCSGO.attributes;

import org.example.selectionOfTrades.models.skinCSGO.attributes.DataWeapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataWeaponRepository extends JpaRepository<DataWeapon, Long> {

    List<DataWeapon> findByWeaponName(String weaponName);
}

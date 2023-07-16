package org.example.selectionOfTrades.repositories.gunSkinCSGO;

import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    List<Skin> findByPrice(Double price);
    Skin findByQualityAndExteriorAndWeapon(Quality quality,
                                           Exterior exterior,
                                           Weapon weapon);
}

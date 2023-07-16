package org.example.selectionOfTrades.services.gunSkinsCSGO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.example.selectionOfTrades.repositories.gunSkinCSGO.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public List<Weapon> listWeapon(String name) {
        if (name != null) weaponRepository.findByName(name);
        return weaponRepository.findAll();
    }

    public void saveWeapon(Weapon weapon) {
        if (weapon != null && weapon.getCollection() != null) {
            try {
                log.info("Saving weapon");
                weaponRepository.save(weapon);
            }
            catch (Exception e){
                log.info(weapon.toString());
            }
        }
    }

    public void saveWeapons(List<Weapon> weapons) {
        if (weapons != null && weapons.size() > 0) {
            weapons.forEach(weapon -> log.info("Saving weapon"));
            weaponRepository.saveAll(weapons);
        }
    }

    public void deleteWeapon(Long id) {
        weaponRepository.deleteById(id);
    }

    public Weapon getWeaponId(Long id) {
        return weaponRepository.findById(id).orElse(null);
    }
}

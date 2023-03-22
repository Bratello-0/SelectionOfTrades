package org.example.selectionOfTrades.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.Weapon;
import org.example.selectionOfTrades.repositories.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public List<Weapon> listWeapons(String name) {
        if(name!= null) weaponRepository.findByName(name);
        return weaponRepository.findAll();
    }

    public void saveWeapon(Weapon weapon) {
        log.info("Saving new {}", weapon.getId());
        weaponRepository.save(weapon);
    }

    public void deleteWeapon(Long id) {
        weaponRepository.deleteById(id);
    }

    public Weapon getWeaponId(Long id) {
        return weaponRepository.findById(id).orElse(null);
    }
}

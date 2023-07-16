package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.attributes.DataWeapon;
import org.example.selectionOfTrades.repositories.attributes.DataWeaponRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataWeaponService {
    private final DataWeaponRepository dataWeaponRepository;

    public List<DataWeapon> listDataWeapon(String weaponName) {
        if (weaponName != null) dataWeaponRepository.findByWeaponName(weaponName);
        return dataWeaponRepository.findAll();
    }

    public DataWeapon getDataWeapon(String tag) {
        return dataWeaponRepository.findByTag(tag);
    }

    public void saveDataWeapon(DataWeapon dataWeapon) {
        printLog(dataWeapon, "Saving dataWeapon");
        dataWeaponRepository.save(dataWeapon);
    }

    public void deleteDataWeapon(Long id) {
        dataWeaponRepository.deleteById(id);
    }

    public DataWeapon getDataWeaponId(Long id) {
        return dataWeaponRepository.findById(id).orElse(null);
    }

    public void saveAllDataWeapons(List<DataWeapon> dataWeaponList) {
        dataWeaponList.forEach((dataWeapon) -> {
            printLog(dataWeapon, "Saving dataWeapon");
        });
        dataWeaponRepository.saveAll(dataWeaponList);
    }

    public void saveAllDataWeapons(Map<String, String> mapDataWeapon) {
        if (mapDataWeapon == null) {
            return;
        }
        List<DataWeapon> dataWeaponListToSave = new ArrayList<>();
        mapDataWeapon.forEach((name, tag) -> dataWeaponListToSave.add(new DataWeapon(name, tag)));

        if (dataWeaponListToSave.size() != 0) {
            saveAllDataWeapons(dataWeaponListToSave);
        }
    }

    private void printLog(DataWeapon dataWeapon, String message) {
        log.info(String.join(" ", message, "name:{}; tag:{};"), dataWeapon.getWeaponName(), dataWeapon.getTag());
    }
}

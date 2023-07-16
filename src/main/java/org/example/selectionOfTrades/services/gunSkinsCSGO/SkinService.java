package org.example.selectionOfTrades.services.gunSkinsCSGO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.example.selectionOfTrades.repositories.gunSkinCSGO.SkinRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkinService {
    private final SkinRepository skinRepository;

    public List<Skin> listSkins(Double price) {
        if (price != null) skinRepository.findByPrice(price);
        return skinRepository.findAll();
    }

    private void saveSkin(Skin skin) {
        Skin skinInBase = skinRepository.findByQualityAndExteriorAndWeapon(skin.getQuality(), skin.getExterior(),skin.getWeapon());
        if(skinInBase != null){
            skinInBase.setPrice(skin.getPrice());
            skinInBase.setQuantity(skin.getQuantity());
            skinRepository.save(skinInBase);
        }
        else {
            skinRepository.save(skin);
        }
        log.info("Saving skin");
    }


    public void saveSkins(List<Skin> skins) {
        if (skins != null && skins.size() > 0) {
            skins.forEach(this::saveSkin);
        }
    }

    public void deleteSkin(Long id) {
        skinRepository.deleteById(id);
    }

    public Skin getSkinId(Long id) {
        return skinRepository.findById(id).orElse(null);
    }
}

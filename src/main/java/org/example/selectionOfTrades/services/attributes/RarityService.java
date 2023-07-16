package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.attributes.Rarity;
import org.example.selectionOfTrades.repositories.attributes.RarityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RarityService {
    private final RarityRepository rarityRepository;

    public List<Rarity> listRarity(String rarity) {
        if (rarity != null) rarityRepository.findByRarity(rarity);
        return rarityRepository.findAll();
    }

    public Rarity getRarity(String tag) {
        return rarityRepository.findByTag(tag);
    }

    public void saveRarity(Rarity rarity) {
        printLog(rarity, "Saving rarity");
        rarityRepository.save(rarity);
    }

    public void deleteRarity(Long id) {
        rarityRepository.deleteById(id);
    }

    public Rarity getRarityId(Long id) {
        return rarityRepository.findById(id).orElse(null);
    }

    public void saveAllRarity(List<Rarity> rarityList) {
        rarityList.forEach((rarity) -> {
            printLog(rarity, "Saving rarity");
        });
        rarityRepository.saveAll(rarityList);
    }

    public void saveAllRarities(Map<String, String> mapRarity) {
        if (mapRarity == null) {
            return;
        }
        List<Rarity> rarityListToSave = new ArrayList<>();
        mapRarity.forEach((name, tag) -> rarityListToSave.add(new Rarity(name, tag)));

        if (rarityListToSave.size() != 0) {
            saveAllRarity(rarityListToSave);
        }
    }


    private void printLog(Rarity rarity, String message) {
        log.info(String.join(" ", message, "name:{}; tag:{};"), rarity.getRarity(), rarity.getTag());
    }
}

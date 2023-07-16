package org.example.selectionOfTrades.models.contract;

import lombok.Getter;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.contract.sorted.SkinsCollection;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Getter
public class SortSkinManeger {

    private final Map<Long, SkinsCollection> sortedSkinsMap = new HashMap<>();

    public Map<Long, SkinsCollection> Sort(List<Skin> skins, List<Collection> collections) {
        collections.forEach(collection -> {
            sortedSkinsMap.put(collection.getId(), new SkinsCollection(collection.getId()));
        });

        skins.forEach(skin -> {
            sortedSkinsMap.get(skin.getWeapon().getCollection().getId()).addSkin(skin);
        });

        sortedSkinsMap.remove(collections.stream().filter(collection -> Objects.equals(collection.getTag(), "set_xraymachine")).findFirst().get().getId());

        sortedSkinsMap.entrySet().forEach(skinsCollection -> {
            final boolean[] isStatTrak = {false};
            skinsCollection.getValue().getSkinsRarityMap().forEach((key, value) -> {
                value.setCountSkins();
                value.getSkinsExteriorMap().forEach((key1, value1) -> {
                    value1.setSkinMinPrice();
                    if (value1.getSkins(true).size() != 0) {
                        isStatTrak[0] = true;
                    }
                });
            });
            skinsCollection.getValue().setStatTrak(isStatTrak[0]);
        });

        return sortedSkinsMap;
    }
}

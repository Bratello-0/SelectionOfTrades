package org.example.selectionOfTrades.models.contract.sorted;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.ExteriorType;
import org.example.selectionOfTrades.enums.RarityType;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;

import java.util.*;

@Getter
@Slf4j
public class SkinsRarity {
    private final Map<Long, SkinExterior> skinsExteriorMap = new HashMap<>();
    private RarityType rarityType;
    private int countSkinsRarity;

    public SkinsRarity(RarityType rarityType) {
        this.rarityType = rarityType;
        skinsExteriorMap.put(ExteriorType.FACTORY_NEW.valueOfIdInBase(), new SkinExterior(ExteriorType.FACTORY_NEW));
        skinsExteriorMap.put(ExteriorType.MINIMAL_WEAR.valueOfIdInBase(), new SkinExterior(ExteriorType.MINIMAL_WEAR));
        skinsExteriorMap.put(ExteriorType.FIELD_TESTED.valueOfIdInBase(), new SkinExterior(ExteriorType.FIELD_TESTED));
        skinsExteriorMap.put(ExteriorType.WELL_WORN.valueOfIdInBase(), new SkinExterior(ExteriorType.WELL_WORN));
        skinsExteriorMap.put(ExteriorType.BATTLE_SCARRED.valueOfIdInBase(), new SkinExterior(ExteriorType.BATTLE_SCARRED));
    }

    public void addSkin(Skin skin) {
        skinsExteriorMap.get(skin.getExterior().getId()).addSkin(skin);
    }

    public void setCountSkins() {
        Set<String> countSkin = new HashSet<>();
        skinsExteriorMap.forEach((key, value) -> value.getSkins().forEach(skin -> {
            countSkin.add((skin.getWeapon().getName() + skin.getWeapon().getDataWeapon().getWeaponName()));
        }));
        countSkinsRarity = countSkin.size();
    }

    public Skin getMinByPriceSkinRarity(boolean isStatTrak, ExteriorType exteriorType) {
        if (isStatTrak) {
            return skinsExteriorMap.get(exteriorType.valueOfIdInBase()).getSkinMinPriceStatTrak();
        }
        return skinsExteriorMap.get(exteriorType.valueOfIdInBase()).getSkinMinPrice();
    }


    public Skin getMinExteriorSkinAllRarities(boolean isStatTrak) {
        return skinsExteriorMap.values().stream().filter(Objects::nonNull)
                .map(skinExterior -> skinExterior.getMinPrice(isStatTrak)).filter(Objects::nonNull)
                .min(Comparator.comparingDouble(Skin::getPrice)).orElse(null);

    }

    public Skin getMaxExteriorSkin(boolean isStatTrak) {
        return skinsExteriorMap.values().stream()
                .map(skinExterior -> skinExterior.getMaxPrice(isStatTrak))
                .max(Comparator.comparingDouble(Skin::getPrice)).orElse(null);
    }
}

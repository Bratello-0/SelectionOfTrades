package org.example.selectionOfTrades.models.contract.sorted;

import lombok.Getter;
import lombok.Setter;
import org.example.selectionOfTrades.enums.ExteriorType;
import org.example.selectionOfTrades.enums.RarityType;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SkinsCollection {
    @Setter
    private boolean isStatTrak;
    private final Long idCollection;
    private final Map<Long, SkinsRarity> skinsRarityMap = new HashMap<>();

    public SkinsCollection(Long idCollection) {
        this.idCollection = idCollection;
        skinsRarityMap.put(RarityType.COVERT.valueOfIdInBase(), new SkinsRarity(RarityType.COVERT));
        skinsRarityMap.put(RarityType.CLASSIFIED.valueOfIdInBase(), new SkinsRarity(RarityType.CLASSIFIED));
        skinsRarityMap.put(RarityType.RESTRICTED.valueOfIdInBase(), new SkinsRarity(RarityType.RESTRICTED));
        skinsRarityMap.put(RarityType.MIL_SPEC_GRADE.valueOfIdInBase(), new SkinsRarity(RarityType.MIL_SPEC_GRADE));
        skinsRarityMap.put(RarityType.INDUSTRIAL_GRADE.valueOfIdInBase(), new SkinsRarity(RarityType.INDUSTRIAL_GRADE));
        skinsRarityMap.put(RarityType.CONSUMER_GRADE.valueOfIdInBase(), new SkinsRarity(RarityType.CONSUMER_GRADE));
    }

    public boolean getIsStatTrak() {
        return isStatTrak;
    }

    public Skin getMinSkinByPriceCollection(boolean isStatTrak, RarityType rarityType, ExteriorType exteriorType) {
        return skinsRarityMap.get(rarityType.valueOfIdInBase()).getMinByPriceSkinRarity(isStatTrak, exteriorType);
    }

    public void addSkin(Skin skin) {
        skinsRarityMap.get(skin.getWeapon().getRarity().getId()).addSkin(skin);
    }
}

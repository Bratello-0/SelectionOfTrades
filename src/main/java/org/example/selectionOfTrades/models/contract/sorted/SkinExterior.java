package org.example.selectionOfTrades.models.contract.sorted;

import lombok.Getter;
import org.example.selectionOfTrades.enums.ExteriorType;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Getter
public class SkinExterior {

    private final ExteriorType exteriorType;

    private Skin skinMinPrice;
    private Skin skinMinPriceStatTrak;

    private final List<Skin> skins;

    public SkinExterior(ExteriorType exteriorType) {
        this.exteriorType = exteriorType;
        skins = new ArrayList<>();
    }

    public void addSkin(Skin skin) {
        skins.add(skin);
    }

    public List<Skin> getSkins(boolean isStatTrak) {
        if(isStatTrak){
            return skins.stream().filter(skin -> skin.getQuality().getId() == 1).toList();
        }
        return skins.stream().filter(skin -> skin.getQuality().getId() == 2).toList();
    }

    public Skin getMaxPrice(boolean isStatTrak) {
        return getSkins(isStatTrak).stream().max(Comparator.comparingDouble(Skin::getPrice)).orElse(null);
    }

    public void setSkinMinPrice(){
        skinMinPrice = getSkins(false).stream().min(Comparator.comparingDouble(Skin::getPrice)).orElse(null);
        skinMinPriceStatTrak = getSkins(true).stream().min(Comparator.comparingDouble(Skin::getPrice)).orElse(null);
    }

    public Skin getMinPrice(boolean isStatTrak) {
        return getSkins(isStatTrak).stream().min(Comparator.comparingDouble(Skin::getPrice)).orElse(null);
    }
}

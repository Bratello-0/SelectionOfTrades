package org.example.selectionOfTrades.models.parser.packeger.data;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GroupDataSkinsAndWeapon {
    private final List<Double> priceList = new ArrayList<>();
    private final List<Long> quantityList = new ArrayList<>();
    private final List<String> exteriorList = new ArrayList<>();

    public void addList(Double price, Long quantity, String exterior) {
        priceList.add(price);
        quantityList.add(quantity);
        exteriorList.add(exterior);
    }

    @Override
    public String toString() {
        return "GroupDataSkinsAndWeapon{" +
                "priceList=" + priceList +
                ", quantityList=" + quantityList +
                ", exteriorList=" + exteriorList +
                '}';
    }
}

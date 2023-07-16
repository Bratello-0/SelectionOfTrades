package org.example.selectionOfTrades.enums;

public enum LinkUrlType {
    ITEM_SET("&category_730_ItemSet[]=tag_"),//collection
    WEAPON("&category_730_Weapon[]="),//date_weapon
    EXTERIOR("&category_730_Exterior[]="),//exterior
    QUALITY("&category_730_Quality[]="),//quality
    RARITY("&category_730_Rarity[]=tag_"),//rarity
    START("&start="),
    NAME("&q=");

    private final String name;

    LinkUrlType(String name) {
        this.name = name;
    }

    public String valueOf() {
        return name;
    }
}

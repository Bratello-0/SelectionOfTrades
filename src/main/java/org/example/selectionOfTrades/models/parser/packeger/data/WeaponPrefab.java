package org.example.selectionOfTrades.models.parser.packeger.data;

import lombok.Getter;

import java.util.Objects;

@Getter
public class WeaponPrefab {

    private final String name;
    private final String gunName;
    private final String imgUrl;
    private String tagCollection;
    private String tagRarities;

    private GroupDataSkinsAndWeapon statTrakData = new GroupDataSkinsAndWeapon();
    private GroupDataSkinsAndWeapon normalData = new GroupDataSkinsAndWeapon();

    public WeaponPrefab(String name, String gunName, String imgUrl) {
        this.name = name;
        this.gunName = gunName;
        this.imgUrl = imgUrl;
    }

    public void setTagCollection(String tagCollection) {
        this.tagCollection = tagCollection;
    }

    public void setTagRarities(String tagRarities) {
        this.tagRarities = tagRarities;
    }

    public WeaponPrefab join(WeaponPrefab weaponPrefab){
        this.statTrakData.getExteriorList().addAll(weaponPrefab.statTrakData.getExteriorList());
        this.statTrakData.getPriceList().addAll(weaponPrefab.statTrakData.getPriceList());
        this.statTrakData.getQuantityList().addAll(weaponPrefab.statTrakData.getQuantityList());
        this.normalData.getExteriorList().addAll(weaponPrefab.normalData.getExteriorList());
        this.normalData.getPriceList().addAll(weaponPrefab.normalData.getPriceList());
        this.normalData.getQuantityList().addAll(weaponPrefab.normalData.getQuantityList());
        return this;
    }

    public WeaponPrefab addList(Double price, Long quantity, String exterior, String statTrak){
        if(statTrak == "Normal"){
            normalData.addList(price, quantity, exterior);
        }else {
            statTrakData.addList(price, quantity, exterior);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponPrefab that = (WeaponPrefab) o;
        return Objects.equals(name, that.name) && Objects.equals(gunName, that.gunName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gunName);
    }

    @Override
    public String toString() {
        return "WeaponPrefab{" +
                "name='" + name + '\'' +
                ", gunName='" + gunName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", tagCollection='" + tagCollection + '\'' +
                ", tagRarities='" + tagRarities + '\'' +
                ", statTrakData=" + statTrakData +
                ", normalData=" + normalData +
                '}';
    }
}

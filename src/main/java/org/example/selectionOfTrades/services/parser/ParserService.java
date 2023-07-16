package org.example.selectionOfTrades.services.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.models.entities.attributes.*;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.example.selectionOfTrades.models.parser.packeger.data.WeaponPrefab;
import org.example.selectionOfTrades.models.parser.Parsed;
import org.example.selectionOfTrades.models.updateTimer.UpdateSkinsTimerTask;
import org.example.selectionOfTrades.services.AttributesService;
import org.example.selectionOfTrades.services.attributes.*;
import org.example.selectionOfTrades.services.contractCSGO.ContractService;
import org.example.selectionOfTrades.services.gunSkinsCSGO.SkinService;
import org.example.selectionOfTrades.services.gunSkinsCSGO.WeaponService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParserService {

    private final AttributesService attributesService;
    private final SkinService skinService;
    private final WeaponService weaponService;
    private final DataWeaponService dataWeaponService;
    private final CollectionService collectionService;
    private final RarityService rarityService;
    private final ExteriorService exteriorService;
    private final QualityService qualityService;

    private final ContractService contractService;
    private int countParse = 0;

    private boolean isParse;

    private final Parsed parser;

    public void updateAttributes(AttributeType attributeType) {
        parser.parseAttributes();
        if (attributesService.saveAttributes(attributeType) > 0) {
            updateWeaponsAndSkins();
        }else{
            startContractService();
        }
    }

    private void updateWeaponsAndSkins() {
        isParse = true;
        List<WeaponPrefab> weaponPrefabs = parser.parseWeaponsAndSkins(collectionService, rarityService);

        Map<String, Exterior> exteriors = new HashMap<>();
        Map<String, Collection> collections = new HashMap<>();
        Map<String, Rarity> rarities = new HashMap<>();
        Map<String, DataWeapon> dataWeapons = new HashMap<>();
        Map<String, Quality> qualities = new HashMap<>();

        exteriorService.listExterior(null).forEach(exterior -> exteriors.put(exterior.getExterior(), exterior));
        collectionService.listCollection(null).forEach(collection -> collections.put(collection.getTag(), collection));
        rarityService.listRarity(null).forEach(rarity -> rarities.put(rarity.getTag(), rarity));
        dataWeaponService.listDataWeapon(null).forEach(dataWeapon -> dataWeapons.put(dataWeapon.getWeaponName(), dataWeapon));
        qualityService.listQuality(null).forEach(quality -> qualities.put(quality.getTag(), quality));

        weaponPrefabs.forEach(weaponPrefab -> {
            Weapon weapon = new Weapon();
            List<Skin> skins = new ArrayList<>();

            log.info("Weapon prefab: {}", weaponPrefab);
            weapon.setName(weaponPrefab.getName());
            weapon.setUrlImg(weaponPrefab.getImgUrl());

            weapon.setCollection(collections.get(weaponPrefab.getTagCollection()));
            weapon.setRarity(rarities.get(weaponPrefab.getTagRarities()));
            weapon.setDataWeapon(dataWeapons.get(weaponPrefab.getGunName()));

            weaponService.saveWeapon(weapon);

            for (int i = 0; i < weaponPrefab.getStatTrakData().getQuantityList().size(); i++) {
                Skin skin = new Skin();
                skin.setPrice(weaponPrefab.getStatTrakData().getPriceList().get(i));
                skin.setQuantity(weaponPrefab.getStatTrakData().getQuantityList().get(i));

                skin.setQuality(qualities.get("strange"));
                skin.setExterior(exteriors.get(weaponPrefab.getStatTrakData().getExteriorList().get(i)));
                skin.setWeapon(weapon);
                skins.add(skin);
            }
            for (int i = 0; i < weaponPrefab.getNormalData().getQuantityList().size(); i++) {
                Skin skin = new Skin();
                skin.setPrice(weaponPrefab.getNormalData().getPriceList().get(i));
                skin.setQuantity(weaponPrefab.getNormalData().getQuantityList().get(i));

                skin.setQuality(qualities.get("normal"));
                skin.setExterior(exteriors.get(weaponPrefab.getNormalData().getExteriorList().get(i)));
                skin.setWeapon(weapon);
                skins.add(skin);
            }
            skinService.saveSkins(skins);
        });
        isParse = false;
        startContractService();
    }

    public void updateSkins() {
        if (isParse) {
            log.info("No update skins; Parse weapons and skins");
            return;
        }
        isParse = true;

        Map<String, Exterior> exteriors = new HashMap<>();
        Map<String, Quality> qualities = new HashMap<>();
        Map<String, Weapon> weapons = new HashMap<>();

        exteriorService.listExterior(null).forEach(exterior -> exteriors.put(exterior.getExterior(), exterior));
        qualityService.listQuality(null).forEach(quality -> qualities.put(quality.getTag(), quality));
        weaponService.listWeapon(null).forEach(weapon -> weapons.put(weapon.getName() + weapon.getDataWeapon().getWeaponName(), weapon));

        List<WeaponPrefab> weaponPrefabs = parser.parseSkins(rarityService);

        weaponPrefabs.forEach(weaponPrefab -> {
            List<Skin> skins = new ArrayList<>();

            log.info("Weapon prefab: {}", weaponPrefab);

            for (int i = 0; i < weaponPrefab.getStatTrakData().getQuantityList().size(); i++) {
                Skin skin = new Skin();
                skin.setPrice(weaponPrefab.getStatTrakData().getPriceList().get(i));
                skin.setQuantity(weaponPrefab.getStatTrakData().getQuantityList().get(i));

                skin.setQuality(qualities.get("strange"));
                skin.setExterior(exteriors.get(weaponPrefab.getStatTrakData().getExteriorList().get(i)));
                skin.setWeapon(weapons.get(weaponPrefab.getName() + weaponPrefab.getGunName()));
                skins.add(skin);
            }
            for (int i = 0; i < weaponPrefab.getNormalData().getQuantityList().size(); i++) {
                Skin skin = new Skin();
                skin.setPrice(weaponPrefab.getNormalData().getPriceList().get(i));
                skin.setQuantity(weaponPrefab.getNormalData().getQuantityList().get(i));

                skin.setQuality(qualities.get("normal"));
                skin.setExterior(exteriors.get(weaponPrefab.getNormalData().getExteriorList().get(i)));
                skin.setWeapon(weapons.get(weaponPrefab.getName() + weaponPrefab.getGunName()));
                skins.add(skin);
            }
            skinService.saveSkins(skins);
        });
        isParse = false;
        startContractService();
    }

    public void startContractService(){
        if(countParse-- <= 0){
            contractService.startCreateContracts(true);
            countParse = 10;
        }else {
            contractService.startCreateContracts(false);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startUpdateDataBase() {

        //startContractService();
        attributesService.loadAttributes();
        updateAttributes(AttributeType.ALL_ATTRIBUTE);

        TimerTask timerTask = new UpdateSkinsTimerTask(this::updateSkins);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 1800 * 1000, 1800 * 1000);//1000mc = 1c
    }
}
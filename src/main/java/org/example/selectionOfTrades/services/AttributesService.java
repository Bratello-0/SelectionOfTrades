package org.example.selectionOfTrades.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.models.parser.packeger.Attributes;
import org.example.selectionOfTrades.services.attributes.*;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttributesService {

    private final Attributes attributes;
    private final CollectionService collectionService;
    private final DataWeaponService dataWeaponService;
    private final ExteriorService exteriorService;
    private final QualityService qualityService;
    private final RarityService rarityService;

    public int saveAttributes(AttributeType attributeType) {
        Map<AttributeType, Map<String, String>> mapAttributes = attributes.getMapAttributesNotSaved();
        if (attributeType != AttributeType.COLLECTIONS) {
            dataWeaponService.saveAllDataWeapons(mapAttributes.get(AttributeType.DATA_WEAPONS));
            exteriorService.saveAllExteriors(mapAttributes.get(AttributeType.EXTERIORS));
            qualityService.saveAllQualities(mapAttributes.get(AttributeType.QUALITIES));
            rarityService.saveAllRarities(mapAttributes.get(AttributeType.RARITIES));
        }
        collectionService.saveAllCollections(mapAttributes.get(AttributeType.COLLECTIONS));
        log.info("End save");
        if(mapAttributes.get(AttributeType.COLLECTIONS) == null) {return 0;}
        return mapAttributes.get(AttributeType.COLLECTIONS).size();
    }

    //при создании бина подгружает данные из бд
    public void loadAttributes() {
        attributes.setMapAttributes(collectionService.listCollection(null),
                exteriorService.listExterior(null),
                dataWeaponService.listDataWeapon(null),
                rarityService.listRarity(null),
                qualityService.listQuality(null));
    }
}

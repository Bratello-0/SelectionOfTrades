package org.example.selectionOfTrades.services;

import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.models.parser.packeger.Attributes;
import org.example.selectionOfTrades.services.attributes.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttributesService {

    private final CollectionService collectionService;
    private final DataWeaponService dataWeaponService;
    private final ExteriorService exteriorService;
    private final QualityService qualityService;
    private final RarityService rarityService;

    public void saveAttributes(Attributes attributes, AttributeType attributeType) {
        Map<AttributeType, Map<String, String>> mapAttributes = attributes.getMapAttributes();

        if (attributeType == AttributeType.COLLECTIONS) {
            collectionService.saveAllCollections(mapAttributes.get(AttributeType.COLLECTIONS));
            return;
        }

        collectionService.saveAllCollections(mapAttributes.get(AttributeType.COLLECTIONS));
        dataWeaponService.saveAllDataWeapons(mapAttributes.get(AttributeType.DATA_WEAPONS));
        exteriorService.saveAllExteriors(mapAttributes.get(AttributeType.EXTERIORS));
        qualityService.saveAllQualities(mapAttributes.get(AttributeType.QUALITIES));
        rarityService.saveAllRarities(mapAttributes.get(AttributeType.RARITIES));
    }
}

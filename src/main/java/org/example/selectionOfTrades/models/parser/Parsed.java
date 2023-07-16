package org.example.selectionOfTrades.models.parser;

import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.parser.packeger.data.WeaponPrefab;
import org.example.selectionOfTrades.services.attributes.CollectionService;
import org.example.selectionOfTrades.services.attributes.RarityService;

import java.util.List;

public interface Parsed {

    void parseAttributes();
    List<WeaponPrefab> parseSkins(RarityService rarityService);

    List<WeaponPrefab> parseWeaponsAndSkins(CollectionService collectionService, RarityService rarityService);
}

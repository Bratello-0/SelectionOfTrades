package org.example.selectionOfTrades.models.parser.packeger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.models.entities.attributes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.selectionOfTrades.enums.AttributeType.*;


@Getter
@Slf4j
//Преобразует Json в map
public class Attributes {
    private final Map<AttributeType, Map<String, String>> mapAttributes = new HashMap<>();

    private final Map<AttributeType, Map<String, String>> mapAttributesNotSaved = new HashMap<>();

    public Map<AttributeType, Map<String, String>> getMapAttributesNotSaved() {
        Map<AttributeType, Map<String, String>> result = new HashMap<>();
        if (mapAttributesNotSaved.size() != 0) {

            mapAttributesNotSaved.entrySet().stream().forEach(mapNS -> {
                Map<String, String> tempMap = mapAttributes.get(mapNS.getKey());
                if (tempMap == null) {
                    tempMap = new HashMap<>();
                    mapAttributes.put(mapNS.getKey(), tempMap);
                }
                tempMap.putAll(mapNS.getValue());
            });
            result.putAll(mapAttributesNotSaved);
            mapAttributesNotSaved.clear();
        }
        return result;
    }

    //В Json с параметрами все словари имеют одинаковую структуру, получаем ключ,
    //кладем в tags, берем значение из "localized_name" в качестве ключа, это будет тег, а name - именем
    private Map<String, String> getMap730Settings(Map<String, Object> $730_Exterior) {
        Map<String, String> mapResult = new HashMap<>();
        Map<String, Object> tags = (Map<String, Object>) $730_Exterior.get("tags");

        tags.forEach((tag, name) -> mapResult.put(((Map<String, String>) name).get("localized_name"), tag));

        return mapResult;
    }

    @JsonProperty("730_Exterior")
    public void setExteriors(Map<String, Object> $730_Exterior) {
        mapAttributes.put(EXTERIORS, getMap730Settings($730_Exterior));
    }

    @JsonProperty("730_ItemSet")
    public void setCollections(Map<String, Object> $730_ItemSet) {
        Map<String, String> temp = getMap730Settings($730_ItemSet);
        temp = temp.entrySet().stream()
                .filter(i -> !(i.getKey().equals("Broken Fang Agents") ||
                        i.getKey().equals("Operation Riptide Agents") ||
                        i.getKey().equals("The Blacksite Collection") ||
                        i.getKey().equals("Shattered Web Agents"))
                )
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        mapAttributes.put(COLLECTIONS, temp);
    }

    @JsonProperty("730_Weapon")
    public void setDataWeapons(Map<String, Object> $730_Weapon) {
        mapAttributes.put(DATA_WEAPONS, getMap730Settings($730_Weapon));
    }

    @JsonProperty("730_Rarity")
    public void setRarity(Map<String, Object> $730_Rarity) {
        Map<String, String> temp = getMap730Settings($730_Rarity);
        temp = temp.entrySet().stream()
                .filter(i -> i.getKey().equals("Consumer Grade") ||
                        i.getKey().equals("Industrial Grade") ||
                        i.getKey().equals("Mil-Spec Grade") ||
                        i.getKey().equals("Restricted") ||
                        i.getKey().equals("Classified") ||
                        i.getKey().equals("Covert"))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        mapAttributes.put(RARITIES, temp);
    }

    @JsonProperty("730_Quality")
    public void setQuality(Map<String, Object> $730_Quality) {
        Map<String, String> temp = getMap730Settings($730_Quality);
        temp = temp.entrySet().stream()
                .filter(i -> i.getKey().equals("Normal") ||
                        i.getKey().equals("StatTrak™"))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        mapAttributes.put(QUALITIES, temp);
    }

    private void setListObjToMapAttributes(AttributeType type, List<IAttribute> attribute) {
        mapAttributes.put(type, new HashMap<>());
        attribute.forEach(obj -> mapAttributes.get(type).put(obj.getContext(), obj.getTag()));
    }

    public void setMapAttributes(List<Collection> collection, List<Exterior> exterior,
                                 List<DataWeapon> dataWeapon, List<Rarity> rarity,
                                 List<Quality> quality) {
        setListObjToMapAttributes(COLLECTIONS, new ArrayList<>(collection));
        setListObjToMapAttributes(EXTERIORS, new ArrayList<>(exterior));
        setListObjToMapAttributes(DATA_WEAPONS, new ArrayList<>(dataWeapon));
        setListObjToMapAttributes(RARITIES, new ArrayList<>(rarity));
        setListObjToMapAttributes(QUALITIES, new ArrayList<>(quality));
    }

    public void join(Attributes readValue) {
        Map<AttributeType, Map<String, String>> inputMap = readValue.getMapAttributes();
        if (mapAttributes.size() == 0) {
            mapAttributesNotSaved.putAll(inputMap);
            return;
        }
        Map<AttributeType, Map<String, String>> notCollisionMap =
                inputMap.entrySet().stream()
                        .filter(mp -> !mapAttributes.containsKey(mp.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        mapAttributesNotSaved.putAll(notCollisionMap);

        inputMap.entrySet().stream()
                .filter(mapI -> mapAttributes.containsKey(mapI.getKey()))
                .forEach(mapI -> {
                    mapI.getValue().entrySet().forEach(lMapI -> {
                        if (!mapAttributes.get(mapI.getKey()).containsKey(lMapI.getKey())) {
                            Map<String, String> tempMap = mapAttributesNotSaved.get(mapI.getKey());
                            if (tempMap == null) {
                                tempMap = new HashMap<>();
                                mapAttributesNotSaved.put(mapI.getKey(), tempMap);
                            }
                            tempMap.put(lMapI.getKey(), lMapI.getValue());
                        }
                    });
                });
    }
}

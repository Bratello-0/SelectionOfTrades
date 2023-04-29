package org.example.selectionOfTrades.models.parser.packeger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.example.selectionOfTrades.enums.AttributeType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
//Преобразует Json в Entity
public class Attributes {
    private final Map<AttributeType, Map<String, String>> mapAttributes = new HashMap<>();

    //В Json с параметрами все словари имеют одинаковую структуру, получаем ключ,
    //кладем в tags, берем значение из "localized_name" в качестве ключа, это будет тег, а name - именем
    private Map<String, String> getMap730Settings(Map<String, Object> $730_Exterior) {
        Map<String, String> mapResult = new HashMap<>();
        Map<String, Object> tags = (Map<String, Object>) $730_Exterior.get("tags");

        tags.forEach((tag, name) -> mapResult.put(((Map<String, String>)name).get("localized_name"), tag));

        return mapResult;
    }

    @JsonProperty("730_Exterior")
    public void setExteriors(Map<String, Object> $730_Exterior) {
        mapAttributes.put(AttributeType.EXTERIORS, getMap730Settings($730_Exterior));
    }

    @JsonProperty("730_ItemSet")
    public void setCollections(Map<String, Object> $730_ItemSet) {
        mapAttributes.put(AttributeType.COLLECTIONS, getMap730Settings($730_ItemSet));
    }

    @JsonProperty("730_Weapon")
    public void setDataWeapons(Map<String, Object> $730_Weapon) {
        mapAttributes.put(AttributeType.DATA_WEAPONS, getMap730Settings($730_Weapon));
    }

    @JsonProperty("730_Rarity")
    public void setRarity(Map<String, Object> $730_Rarity) {
        mapAttributes.put(AttributeType.RARITIES, getMap730Settings($730_Rarity));
    }

    @JsonProperty("730_Quality")
    public void setQuality(Map<String, Object> $730_Quality) {
        mapAttributes.put(AttributeType.QUALITIES, getMap730Settings($730_Quality));
    }
}

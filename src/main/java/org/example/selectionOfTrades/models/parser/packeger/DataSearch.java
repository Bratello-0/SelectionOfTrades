package org.example.selectionOfTrades.models.parser.packeger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.LinkUrlType;
import org.example.selectionOfTrades.models.parser.packeger.data.WeaponPrefab;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
public class DataSearch {

    private int totalCount = 0;
    private final Map<String, WeaponPrefab> dataWeapons = new HashMap<>();

    @JsonProperty("total_count")
    public void setCount(String count) {
        totalCount = Integer.parseInt(count);
    }

    @JsonProperty("results")
    public void setResult(Map<String, Object>[] result) {
        if (result.length == 0) {
            return;
        }
        if(splitData((String) Arrays.stream(result).toList().stream().findFirst().get().get("name"))[3] == null){
            return;
        }
        Arrays.stream(result).toList().forEach(data -> {
            String[] nameArr = splitData((String) data.get("name"));
            if (nameArr[2] == null) {
                return;
            }
            String key = (nameArr[2] + nameArr[1]);
            if (dataWeapons.containsKey(key)) {
                dataWeapons.get(key).addList(
                        Double.parseDouble(data.get("sell_price").toString()) / 100.0,
                        Long.parseLong(data.get("sell_listings").toString()),
                        nameArr[3],
                        nameArr[0]);
            } else {
                dataWeapons.put(key, new WeaponPrefab(nameArr[2], nameArr[1], ((Map<String, Object>)data.get("asset_description")).get("icon_url").toString())
                        .addList(
                                Double.parseDouble(data.get("sell_price").toString()) / 100,
                                Long.parseLong(data.get("sell_listings").toString()),
                                nameArr[3],
                                nameArr[0])
                );
            }
        });
    }

    public String[] splitData(String in) {
        String[] result = new String[4];
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)+$");
        Matcher m = pattern.matcher(new StringBuilder(in));
        if (m.find()) {
            in = in.replace(m.group(0), "| " + m.group(1)).
                    replaceAll("™", "™ |");
        }
        String[] temp = in.split(" \\| ");
        if (temp.length <= 3) {
            System.arraycopy(temp, 0, result, 1, temp.length);
            result[0] = "Normal";
        } else {
            result = temp;
        }
        return result;
    }

    public DataSearch setTypeAndTag(LinkUrlType type, String tag) {
        dataWeapons.forEach((key, value) -> {
            if (type == LinkUrlType.ITEM_SET) {
                value.setTagCollection(tag);
                return;
            }
            if (type == LinkUrlType.RARITY) {
                value.setTagRarities(tag);
            }
        });
        return this;
    }
}
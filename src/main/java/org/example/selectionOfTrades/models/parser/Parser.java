package org.example.selectionOfTrades.models.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.LinkUrlType;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.attributes.IAttribute;
import org.example.selectionOfTrades.models.entities.attributes.Rarity;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.parser.link.Link;
import org.example.selectionOfTrades.models.parser.link.LinkHandler;
import org.example.selectionOfTrades.models.parser.packeger.DataSearch;
import org.example.selectionOfTrades.models.parser.packeger.JsonPackaged;
import org.example.selectionOfTrades.models.parser.packeger.data.WeaponPrefab;
import org.example.selectionOfTrades.services.attributes.CollectionService;
import org.example.selectionOfTrades.services.attributes.RarityService;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
@Slf4j
public class Parser implements Parsed {

    private final JsonPackaged jsonPackaged;
    private final LinkHandler linkHandler;


    private Document getPage() {
        while (true) {
            Link url = linkHandler.getUrlAttributes();
            try {
                return Jsoup.connect(url.toString())
                        .cookie("browserid", "2915557117417551403")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 OPR/98.0.0.0")
                        .referrer("https://steamcommunity.com/market/search?q=victoria&category_730_ItemSet%5B%5D=tag_set_weapons_iii&category_730_ProPlayer%5B%5D=any&category_730_StickerCapsule%5B%5D=any&category_730_TournamentTeam%5B%5D=any&category_730_Weapon%5B%5D=any&category_730_Exterior%5B%5D=tag_WearCategory2&category_730_Quality%5B%5D=tag_normal&category_730_Quality%5B%5D=tag_strange&appid=730")
                        .timeout(20000)
                        .get();
            } catch (HttpStatusException httpStatusException) {
                if (httpStatusException.getStatusCode() == 429) {
                    log.info("Exception 429 code");
                    sleep(75);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getJson(String url) {
        while (true) {
            try {
                return Jsoup.connect(url)
                        .cookie("browserid", "2915557117417551403")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 OPR/98.0.0.0")
                        .referrer("https://steamcommunity.com/market/search?q=victoria&category_730_ItemSet%5B%5D=tag_set_weapons_iii&category_730_ProPlayer%5B%5D=any&category_730_StickerCapsule%5B%5D=any&category_730_TournamentTeam%5B%5D=any&category_730_Weapon%5B%5D=any&category_730_Exterior%5B%5D=tag_WearCategory2&category_730_Quality%5B%5D=tag_normal&category_730_Quality%5B%5D=tag_strange&appid=730")
                        .timeout(60000)
                        .ignoreContentType(true)
                        .execute()
                        .body();
            } catch (HttpStatusException httpStatusException) {
                if (httpStatusException.getStatusCode() == 429) {
                    log.info("Exception 429 code");
                    sleep(25);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sleep(int timeSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String findScriptWithData() {
        Document document = getPage();
        Element script = document.selectFirst("body > " +
                ".responsive_page_menu_ctn mainmenu > " +
                ".responsive_page_content > " +
                ".responsive_page_template_content, " +
                "script[type]:not([src])" +
                ":containsData(g_rgFilterData)");

        return script.toString();
    }

    //из скрипта на опр строчке достаем нужную строку, и очищаем от лишнего, для преобразования в Json
    private String getJsonFromStringScript(String scriptWithData) {
        String jsonFilterData;
        jsonFilterData = scriptWithData.split("\r", 0)[10].split(" ", 4)[3];
        jsonFilterData = jsonFilterData.substring(0, jsonFilterData.length() - 1);
        return jsonFilterData;
    }

    @Override
    public void parseAttributes() {
        String jsonString = getJsonFromStringScript(findScriptWithData());
        jsonPackaged.jsonToAttributes(jsonString);
    }

    @Override
    public List<WeaponPrefab> parseSkins(RarityService rarityService) {
        List<Link> urlsRarities = getLinks(LinkUrlType.RARITY,
                new ArrayList<>(rarityService.listRarity(null)),
                0);
        return getWeaponPrefabs(urlsRarities);
    }

    @Override
    public List<WeaponPrefab> parseWeaponsAndSkins(CollectionService collectionService, RarityService rarityService) {
        List<Link> urlCollections = getLinks(LinkUrlType.ITEM_SET,
                new ArrayList<>(collectionService.listCollection(null)),
                -1);

        List<Link> urlsRarities = getLinks(LinkUrlType.RARITY,
                new ArrayList<>(rarityService.listRarity(null)),
                0);
        log.info("count links : {}", urlCollections.size() + urlsRarities.size());

        return joinWeaponPrefab(getWeaponPrefabs(urlCollections), getWeaponPrefabs(urlsRarities));
    }

    private List<WeaponPrefab> joinWeaponPrefab(List<WeaponPrefab> weaponPrefabs1, List<WeaponPrefab> weaponPrefabs2) {
        weaponPrefabs2.forEach(weaponPrefab2 -> {
            weaponPrefabs1.stream().filter(weaponPrefab1 -> weaponPrefab1.equals(weaponPrefab2)).findFirst().ifPresent(temp -> weaponPrefab2.setTagCollection(temp.getTagCollection()));
        });
        return weaponPrefabs2;
    }

    private List<Link> getLinks(LinkUrlType type, List<IAttribute> attributes, int isStatTrak) {
        return new ArrayList<>(linkHandler.getUrls(type,
                attributes,
                isStatTrak));
    }

    private List<Link> getLinks(DataSearch dataSearch, Link link) {
        if (dataSearch.getTotalCount() >= 100) {
            return linkHandler.getNextPages(List.of(link), List.of(dataSearch.getTotalCount()));
        }
        return null;
    }

    private List<WeaponPrefab> getWeaponPrefabs(List<Link> links) {
        if (links == null || links.size() == 0) {
            return new ArrayList<>();
        }
        Map<WeaponPrefab, WeaponPrefab> weaponPrefabMap = new HashMap<>();

        links.forEach(link -> {
            log.info("Link;  tag: {}; page: {}; url: {};", link.getTag(), link.getPage(), link);
            DataSearch dataSearchTemp = jsonPackaged.jsonToDataSearch(
                    getJson(link.toString())).setTypeAndTag(link.getType(), link.getTag()
            );
            addWeaponPrefab(weaponPrefabMap, dataSearchTemp.getDataWeapons().values().stream().toList());

            List<Link> linksNew = getLinks(dataSearchTemp, link);
            if (linksNew != null && linksNew.size() != 0) {

                linksNew.forEach(linkNew -> {
                    log.info("Link;  tag: {}; page: {}; url: {};", linkNew.getTag(), linkNew.getPage(), linkNew);
                    DataSearch dataSearchTempNew = jsonPackaged.jsonToDataSearch(
                            getJson(linkNew.toString())).setTypeAndTag(linkNew.getType(), linkNew.getTag()
                    );
                    addWeaponPrefab(weaponPrefabMap, dataSearchTempNew.getDataWeapons().values().stream().toList());
                });
            }
        });
        return weaponPrefabMap.values().stream().toList();
    }

    private void addWeaponPrefab(Map<WeaponPrefab, WeaponPrefab> weaponPrefabMap, List<WeaponPrefab> weaponPrefabsInput) {
        weaponPrefabsInput.forEach(weaponPrefab -> {
            if (weaponPrefabMap.containsKey(weaponPrefab)) {
                weaponPrefabMap.get(weaponPrefab).join(weaponPrefab);
            } else {
                weaponPrefabMap.put(weaponPrefab, weaponPrefab);
            }
        });
    }
}

package org.example.selectionOfTrades.models.parser;

import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.models.parser.packeger.JsonPackaged;
import org.example.selectionOfTrades.models.parser.packeger.Attributes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class Parser implements Parsed {

    private final JsonPackaged jsonPackaged;
    //private LinkHandler linkHandler;


    private Document getPage() throws IOException {
        String url = "https://steamcommunity.com/market/search?q=&category_730_ItemSet%5B%5D=any" +
                "&category_730_ProPlayer%5B%5D=any" +
                "&category_730_StickerCapsule%5B%5D=any" +
                "&category_730_TournamentTeam%5B%5D=any" +
                "&category_730_Weapon%5B%5D=any" +
                "&appid=730";
        return Jsoup.connect(url)
                .referrer("https://www.google.com")
                .timeout(10000)
                .get();
    }

    private String findScriptWithData() {
        String scriptWithData = null;
        try {
            Document document = getPage();
            Element script = document.selectFirst("body > " +
                    ".responsive_page_menu_ctn mainmenu > " +
                    ".responsive_page_content > " +
                    ".responsive_page_template_content, " +
                    "script[type]:not([src])" +
                    ":containsData(g_rgFilterData)");

            scriptWithData = script.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scriptWithData;
    }

    //из скрипта на опр строчке достаем нужную строку, и очищаем от лишнего, для преобразования в Json
    private String getJsonFromStringScript(String scriptWithData) {
        String jsonFilterData;
        jsonFilterData = scriptWithData.split("\r", 0)[10].split(" ", 4)[3];
        jsonFilterData = jsonFilterData.substring(0, jsonFilterData.length() - 1);
        return jsonFilterData;
    }

    @Override
    public Attributes getAttributes() {
        String jsonString = getJsonFromStringScript(findScriptWithData());

        return jsonPackaged.stringToAttributes(jsonString);
    }
}

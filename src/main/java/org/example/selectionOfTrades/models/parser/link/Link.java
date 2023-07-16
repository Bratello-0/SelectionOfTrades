package org.example.selectionOfTrades.models.parser.link;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.selectionOfTrades.enums.LinkUrlType;

import static org.example.selectionOfTrades.enums.LinkUrlType.QUALITY;
import static org.example.selectionOfTrades.enums.LinkUrlType.START;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Link {

    private LinkUrlType type;
    private String tag;

    /**
     * quality:
     * normal < 0 < statTrak
     * 0 == statTrakAndNormal
     */
    private int StatTrak;

    private int page = 0;
    private String startUrl;

    public Link(LinkUrlType type, String tag, int isStatTrak) {
        this(type, tag);
        this.setStatTrak(isStatTrak);
    }

    public Link(Link link, int page) {
        this(link.type, link.tag, link.getStatTrak());
        this.setPage(page);
    }

    public Link(LinkUrlType type, String tag) {
        this.setType(type);
        this.setTag(tag);
        this.setStartUrl("https://steamcommunity.com/market/search/render/?query=" +
                "&appid=730" +
                "&norender=1" +
                "&search_descriptions=0" +
                "&sort_column=name" +
                "&sort_dir=asc" +
                "&category_730_Type[]=tag_CSGO_Type_Pistol" +
                "&category_730_Type[]=tag_CSGO_Type_SMG" +
                "&category_730_Type[]=tag_CSGO_Type_Rifle" +
                "&category_730_Type[]=tag_CSGO_Type_SniperRifle" +
                "&category_730_Type[]=tag_CSGO_Type_Shotgun" +
                "&category_730_Type[]=tag_CSGO_Type_Machinegun" +
                "&count=100");
        this.setPage(0);
    }

    public Link() {
        this.setStartUrl("https://steamcommunity.com/market/search?appid=730");
    }

    public void setPage(int inputPage) {
        if (inputPage < 0) {
            this.page = 0;
            return;
        }
        this.page = inputPage;
    }

    private String booleanToStringStatTrak() {
        if (getStatTrak() == 0) {
            return QUALITY.valueOf() + "tag_strange" + QUALITY.valueOf() + "tag_normal";

        }
        if (0 < getStatTrak()) {
            return QUALITY.valueOf() + "tag_strange";
        }
        return QUALITY.valueOf() + "tag_normal";
    }

    @Override
    public String toString() {
        if (startUrl.endsWith("?appid=730")) {
            return startUrl;
        }
        return startUrl +
                this.getType().valueOf() +
                this.getTag() +
                booleanToStringStatTrak() +
                START.valueOf() +
                (getPage() * 100);
    }
}
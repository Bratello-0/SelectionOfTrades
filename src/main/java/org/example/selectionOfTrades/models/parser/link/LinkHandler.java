package org.example.selectionOfTrades.models.parser.link;

import org.example.selectionOfTrades.enums.LinkUrlType;
import org.example.selectionOfTrades.models.entities.attributes.IAttribute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkHandler {

    public Link getUrlAttributes() {
        return new Link();
    }

    public List<Link> getNextPages(List<Link> links, List<Integer> ints) {
        int[] index = {0};
        List<Link> linkUrl = new ArrayList<>();
        links.forEach(link -> {
            for (int j = 1; j < (int)Math.ceil((double) ints.get(index[0]) / 100); j++) {
                linkUrl.add(new Link(link, j));
            }
            index[0]++;
        });
        return linkUrl;
    }

    public List<Link> getUrls(LinkUrlType type, List<IAttribute> attributes, int isStatTrak) {
        List<Link> linkUrl = new ArrayList<>();
        attributes.forEach(attribute -> {
            linkUrl.add(new Link(type, attribute.getTag(), isStatTrak));
        });
        return linkUrl;
    }
}
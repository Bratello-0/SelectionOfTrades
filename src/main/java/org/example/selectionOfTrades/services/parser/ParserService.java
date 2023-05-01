package org.example.selectionOfTrades.services.parser;

import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.models.parser.Parsed;
import org.example.selectionOfTrades.services.AttributesService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserService {

    private final AttributesService attributesService;

    private final Parsed parser;

    public void parseAttributes(AttributeType attributeType) {
        attributesService.saveAttributes(parser.getAttributes(), attributeType);
    }
}
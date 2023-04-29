package org.example.selectionOfTrades.services;

import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.interfaceModels.parser.Parser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserService {

    private final AttributesService attributesService;

    private final Parser parser;

    public void parseAttributes(AttributeType attributeType) {
        attributesService.saveAttributes(parser.getAttributes(), attributeType);
    }
}
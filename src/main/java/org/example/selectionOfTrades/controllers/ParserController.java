package org.example.selectionOfTrades.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.AttributeType;
import org.example.selectionOfTrades.services.ParserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ParserController {
    private final ParserService parserService;

    @GetMapping("/first_start")
    @ResponseBody
    public String firstStart() {
        parserService.parseAttributes(AttributeType.ALL_ATTRIBUTE);
        return "First start";
    }

    @GetMapping("/parser/update")
    @ResponseBody
    public String update() {
        parserService.parseAttributes(AttributeType.COLLECTIONS);
        return "Update";
    }
}

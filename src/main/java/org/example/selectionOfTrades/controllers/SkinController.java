package org.example.selectionOfTrades.controllers;


import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.services.SkinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class SkinController {
    private final SkinService skinService;

    @GetMapping("/")
    public String weapon(@RequestParam(name = "price", required = false) Double price, Model model) {
        model.addAttribute("skins", skinService.listSkins(price));
        return "skin";
    }
}

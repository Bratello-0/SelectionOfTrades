package org.example.selectionOfTrades.controllers;

import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.services.attributes.CollectionService;
import org.example.selectionOfTrades.services.gunSkinsCSGO.WeaponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TradeController {
    private final WeaponService weaponService;
    private final CollectionService collection;

    @GetMapping("/trade/weapon")
    public String indexWeapon(Model model) {
        model.addAttribute("weapons", weaponService.listWeapon(null));
        model.addAttribute("collections", collection.listCollection(null));
        return "index";
    }
    @GetMapping("/trade/contract")
    public String getContract(Model model) {
        model.addAttribute("weapons", weaponService.listWeapon(null));
        model.addAttribute("collections", collection.listCollection(null));
        return "contract";
    }
}

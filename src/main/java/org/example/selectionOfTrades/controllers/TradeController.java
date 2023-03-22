package org.example.selectionOfTrades.controllers;


import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.Services.WeaponService;
import org.example.selectionOfTrades.models.Weapon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class TradeController {
    private final WeaponService weaponService;

    @GetMapping("/")
    public String weapon(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("weapons", weaponService.listWeapons(name));
        return "weapon";
    }
}

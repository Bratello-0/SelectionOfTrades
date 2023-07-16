package org.example.selectionOfTrades.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.services.gunSkinsCSGO.WeaponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping("/weapon")
    public String getSkins(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("weapons", weaponService.listWeapon(name));
        log.info("get Weapon");
        return "weapon";
    }
}

package org.example.selectionOfTrades.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.services.contractCSGO.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/contract")
    public String getContract(Model model) {
        model.addAttribute("contracts", contractService.listContract(null).stream().filter(contract-> contract.getCoefficient()<2).toList());
        log.info("get Contracts");
        return "contracts";
    }
}

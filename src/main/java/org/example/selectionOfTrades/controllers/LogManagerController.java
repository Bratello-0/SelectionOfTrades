package org.example.selectionOfTrades.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.services.parser.LogManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LogManagerController {

    private final LogManagerService logManagerService;

    @GetMapping("/log")
    public String getLogFile(Model model, HttpServletRequest request) {
        List<String> logStrings = new ArrayList<>();
        log.info("Getting log! Ip:{}", request.getRemoteAddr());

        try {
            logStrings = logManagerService.getListLog();
        } catch (IOException e) {
            log.error("Url:{}; File is not found", request.getRequestURI());
            logStrings.add("Error; File is not found;");
        }

        model.addAttribute("linesLog", logStrings);
        return "log";
    }
}

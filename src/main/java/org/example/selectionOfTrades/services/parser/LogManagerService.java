package org.example.selectionOfTrades.services.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.log.LogManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogManagerService {

    private final LogManager logManager;

    public List<String> getListLog() throws IOException {
        return logManager.getLog();
    }

}
package org.example.selectionOfTrades.models.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class LogManager {

    @Value("${logging.file.name}")
    private String pathLog;


    public List<String> getLog() throws IOException {
        return Files.readAllLines(Paths.get(pathLog), StandardCharsets.UTF_8);
    }
}

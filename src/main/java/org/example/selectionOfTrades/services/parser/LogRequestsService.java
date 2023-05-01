package org.example.selectionOfTrades.services.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.parser.LogRequests;
import org.example.selectionOfTrades.repositories.parser.LogRequestsRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogRequestsService {

    private final LogRequestsRepository logRequestsRepository;

    public void saveDateUpdateData(LogRequests logRequests){
        log.info("Update Data; Message:{}", logRequests.getMessage());
        logRequestsRepository.save(logRequests);
    }

}

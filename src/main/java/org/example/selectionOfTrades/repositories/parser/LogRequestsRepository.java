package org.example.selectionOfTrades.repositories.parser;

import org.example.selectionOfTrades.models.entities.parser.LogRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRequestsRepository extends JpaRepository<LogRequests, Long> {
}

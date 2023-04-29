package org.example.selectionOfTrades.models.parser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests_Steam")
public class DateUpdateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "message", columnDefinition = "text")
    private String message;

    @PrePersist
    private void init() {
        date = LocalDateTime.now();
    }
}

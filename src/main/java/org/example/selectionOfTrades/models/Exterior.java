package org.example.selectionOfTrades.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Exterior")
public class Exterior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "exterior", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String exterior;
    @Column(name = "tag_exterior", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;


}

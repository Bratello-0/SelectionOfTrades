package org.example.selectionOfTrades.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "category", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String category;
    @Column(name = "tag_category", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;

}

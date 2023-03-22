package org.example.selectionOfTrades.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "case", columnDefinition = "varchar", length = 40, nullable = false)
    private String caseName;
    @Column(name = "tag_case", columnDefinition = "varchar", length = 40, nullable = false)
    private String tag;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private DataWeapon dataWeapon;
}

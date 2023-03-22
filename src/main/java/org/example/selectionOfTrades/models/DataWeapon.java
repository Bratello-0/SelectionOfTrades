package org.example.selectionOfTrades.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Data_weapon")
public class DataWeapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar", length = 60, unique = true, nullable = false)
    private String name;
    @Column(name = "tag_weapon", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag_weapon;

    @Column(name = "collection_id", nullable = false)
    private Long collectionId;
    @Column(name = "quantity_id", nullable = false)
    private Long qualityId;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "dataWeapon")
    private Collection collection;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "dataWeapon")
    private Quality quality;
}

package org.example.selectionOfTrades.models.entities.attributes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_case", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String caseName;
    @Column(name = "tag_case", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;

    public Collection(String caseName, String tag) {
        this.caseName = caseName;
        this.tag = tag;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "collection")
    private List<Weapon> weapon;
}

package org.example.selectionOfTrades.models.skinCSGO.attributes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.skinCSGO.Weapon;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rarity")
public class Rarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rarity", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String rarity;
    @Column(name = "tag_rarity", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;

    public Rarity(String rarity, String tag) {
        this.rarity = rarity;
        this.tag = tag;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "rarity")
    private List<Weapon> weapon;
}

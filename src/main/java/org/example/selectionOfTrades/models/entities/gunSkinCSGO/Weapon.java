package org.example.selectionOfTrades.models.entities.gunSkinCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.models.entities.attributes.DataWeapon;
import org.example.selectionOfTrades.models.entities.attributes.Rarity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Weapon")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "varchar", length = 60)
    private String name;

    @Column(name = "url_img", columnDefinition = "varchar", unique = true, length = 512)
    private String urlImg;

    @JoinColumn(name = "collectionId", nullable = false)
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Collection collection;
    @JoinColumn(name = "rarityId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Rarity rarity;
    @JoinColumn(name = "dataWeaponId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DataWeapon dataWeapon;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "weapon")
    private List<Skin> skins;

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + name +
                ", collection=" + collection.getCaseName() +
                ", rarity=" + rarity.getRarity() +
                ", dataWeapon=" + dataWeapon.getWeaponName() +
                '}';
    }
}

package org.example.selectionOfTrades.models.entities.gunSkinCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.models.entities.contractCSGO.Contract;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Skin", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueQualityAndExteriorAndWeapon",
                columnNames = {"qualityId", "exteriorId", "weaponId"})}
)
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Long quantity;

    @JoinColumn(name = "qualityId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Quality quality;

    @JoinColumn(name = "exteriorId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Exterior exterior;

    @JoinColumn(name = "weaponId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Weapon weapon;

    @Override
    public String toString() {
        return "Skin{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", quality=" + quality.getQuality() +
                ", exterior=" + exterior.getExterior() +
                ", weapon=" + weapon.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skin skin = (Skin) o;
        return Objects.equals(quality, skin.quality) && Objects.equals(exterior, skin.exterior) && Objects.equals(weapon, skin.weapon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quality, exterior, weapon);
    }
}


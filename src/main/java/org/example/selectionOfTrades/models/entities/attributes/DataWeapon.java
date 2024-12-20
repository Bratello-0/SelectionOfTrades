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
@Table(name = "Data_Weapon")
public class DataWeapon implements IAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "weapon", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String weaponName;
    @Column(name = "tag_weapon", columnDefinition = "varchar", length = 40)
    private String tag;

    public DataWeapon(String weaponName, String tag_weapon) {
        this.weaponName = weaponName;
        this.tag = tag_weapon;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "dataWeapon")
    private List<Weapon> weapon;

    @Override
    public String getContext() {
        return weaponName;
    }
}

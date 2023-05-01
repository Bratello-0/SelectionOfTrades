package org.example.selectionOfTrades.models.entities.attributes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;

import java.util.List;

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

    public Exterior(String exterior, String tag) {
        this.exterior = exterior;
        this.tag = tag;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "exterior")
    private List<Skin> skin;
}
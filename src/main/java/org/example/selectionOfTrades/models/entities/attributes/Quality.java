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
@Table(name = "Quality")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "quality", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String quality;
    @Column(name = "tag_quality", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;

    public Quality(String quality, String tag){
        this.quality = quality;
        this.tag = tag;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "quality")
    private List<Skin> skin;
}

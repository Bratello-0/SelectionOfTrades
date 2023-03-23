package org.example.selectionOfTrades.models.skinCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Quality")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "quality", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String type;
    @Column(name = "tag_quality", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String tag;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "quality")
    private List<Weapon> weapon;
}

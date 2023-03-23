package org.example.selectionOfTrades.models.skinCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "collection")
    private List<Weapon> weapon;
}

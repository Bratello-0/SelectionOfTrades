package org.example.selectionOfTrades.models.entities.attributes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Collection")
public class Collection implements IAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_case", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String caseName;
    @Column(name = "tag_case", columnDefinition = "varchar", length = 40)
    private String tag;

    public Collection(String caseName, String tag) {
        this.caseName = caseName;
        this.tag = tag;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "collection")
    private List<Weapon> weapon;

    @Override
    public String getContext() {
        return caseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collection that = (Collection) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", caseName='" + caseName + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}

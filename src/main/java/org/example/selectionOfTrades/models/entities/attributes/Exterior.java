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
public class Exterior implements IAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "exterior", columnDefinition = "varchar", length = 40, unique = true, nullable = false)
    private String exterior;
    @Column(name = "tag_exterior", columnDefinition = "varchar", length = 40)
    private String tag;
    @Column(name = "float")
    private Double floatExterior;

    public Exterior(String exterior, String tag) {
        this.exterior = exterior;
        this.tag = tag;
        floatExterior = floatByName(tag);
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "exterior")
    private List<Skin> skin;

    @Override
    public String getContext() {
        return exterior;
    }

    public static Double floatByName(String tag){
        char idExterior = tag.charAt(tag.length()-1);
        switch (idExterior){
            case'0':
                return 0.00001d;
            case'1':
                return 0.07001d;
            case'2':
                return 0.15001d;
            case'3':
                return 0.38001d;
            case'4':
                return 0.45001d;
            default:
                return -1.;
        }
    }

    @Override
    public String toString() {
        return "Exterior{" +
                ", floatExterior=" + String.format("%.6f", floatExterior) +
                '}';
    }
}

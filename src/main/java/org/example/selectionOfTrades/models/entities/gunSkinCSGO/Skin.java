package org.example.selectionOfTrades.models.entities.gunSkinCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.models.entities.attributes.Quality;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Skin")
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price", columnDefinition = "money", nullable = false)
    private Double price;
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Quality quality;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Exterior exterior;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Weapon weapon;
}


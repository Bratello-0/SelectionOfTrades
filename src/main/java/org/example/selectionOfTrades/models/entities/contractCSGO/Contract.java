package org.example.selectionOfTrades.models.entities.contractCSGO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.models.entities.attributes.Rarity;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.tuple.Tuple;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "coefficient")
    private Double coefficient;

    @JoinColumn(name = "skinId1")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin1;

    @JoinColumn(name = "skinId2")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin2;

    @JoinColumn(name = "skinId3")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin3;

    @JoinColumn(name = "skinId4")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin4;

    @JoinColumn(name = "skinId5")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin5;

    @JoinColumn(name = "skinId6")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin6;

    @JoinColumn(name = "skinId7")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin7;

    @JoinColumn(name = "skinId8")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin8;

    @JoinColumn(name = "skinId9")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin9;

    @JoinColumn(name = "skinId10")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Skin skin10;

    @JoinColumn(name = "rarityId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Rarity rarity;

    @JoinColumn(name = "qualityId")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Quality quality;

    public boolean setSkins(List<Tuple<Integer, Skin>> skins){
        if(skins.size() <= 10 && skins.size() > 0){
            if(skins.stream().mapToInt(skin->skin._1).sum() == 10){
                final int[] iter = {0};
                Skin[] skinsArr = new Skin[10];
                skins.stream().forEach(skinl->{
                    for (int i = 0; i < skinl._1; i++) {
                        skinsArr[iter[0]++] = skinl._2;
                    }
                });
                skin1 = skinsArr[0];
                skin2 = skinsArr[1];
                skin3 = skinsArr[2];
                skin4 = skinsArr[3];
                skin5 = skinsArr[4];
                skin6 = skinsArr[5];
                skin7 = skinsArr[6];
                skin8 = skinsArr[7];
                skin9 = skinsArr[8];
                skin10 = skinsArr[9];
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", price=" + price +
                ", coefficient=" + coefficient +
                ", \nskin1=" + skin1 +
                ", \nskin2=" + skin2 +
                ", \nskin3=" + skin3 +
                ", \nskin4=" + skin4 +
                ", \nskin5=" + skin5 +
                ", \nskin6=" + skin6 +
                ", \nskin7=" + skin7 +
                ", \nskin8=" + skin8 +
                ", \nskin9=" + skin9 +
                ", \nskin10=" + skin10 +
                ", rarity=" + rarity.getRarity() +
                ", quality=" + quality.getQuality() +
                '}';
    }
}

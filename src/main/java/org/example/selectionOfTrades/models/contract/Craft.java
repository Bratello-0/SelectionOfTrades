package org.example.selectionOfTrades.models.contract;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.ExteriorType;
import org.example.selectionOfTrades.enums.RarityType;
import org.example.selectionOfTrades.models.contract.sorted.SkinExterior;
import org.example.selectionOfTrades.models.contract.sorted.SkinsCollection;
import org.example.selectionOfTrades.models.entities.contractCSGO.Contract;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.tuple.Tuple;

import java.util.*;



@Slf4j
public class Craft {
    private Map<Long, SkinsCollection> skinsCollectionMap;
    @Getter(value = AccessLevel.PUBLIC)
    // double is chance
    private final List<Tuple<Double, Skin>> skinsResultCraft = new ArrayList<>();
    @Getter(value = AccessLevel.PUBLIC)
    private Contract contract;
    @Getter(value = AccessLevel.PUBLIC)
    private double averageFloatContract;

    public Craft(Contract contract, Map<Long, SkinsCollection> skinsCollectionMap) {
        this.skinsCollectionMap = skinsCollectionMap;
        this.contract = contract;

        defaultMethod();
        if(this.contract.getCoefficient() > 2 || this.contract.getCoefficient() < 0.05){
            log.info(this.toString());
        }
    }

    public Craft(List<Skin> skinsToContract, Map<Long, SkinsCollection> skinsCollectionMap) {
        this.skinsCollectionMap = skinsCollectionMap;
        contract = new Contract();
        setSkinsContract(skinsToContract);

        defaultMethod();
        if(this.contract.getCoefficient() > 2 || this.contract.getCoefficient() < 0.05){
            log.info(this.toString());
        }
    }

    private Double roundUpDouble(double value){
        double scale = Math.pow(10, 5);
        return Math.ceil(value * scale) / scale;
    }

    private void defaultMethod() {
        contract.setQuality(contract.getSkin1().getQuality());
        contract.setPrice(roundUpDouble(getPrice()));
        contract.setRarity(contract.getSkin1().getWeapon().getRarity());
        setAverageFloatContract();
        setSkinsResultCraft();
        contract.setCoefficient(roundUpDouble(getCoefficient()));
    }

    private void setSkinsResultCraft() {
        Set<Skin> skinsSet = new HashSet<>(joinSkinInContract());
        List<SkinExterior> skinsNextRarity = new ArrayList<>();
        skinsSet.forEach(skin -> {
            skinsNextRarity.addAll(
                    skinsCollectionMap.get(skin.getWeapon().getCollection().getId())
                            .getSkinsRarityMap().get(getNextRarity().valueOfIdInBase()).getSkinsExteriorMap().values()
            );
        });
        setChanceDropSkin(getSkinResultCraft(skinsNextRarity));
    }

    private Set<Skin> getSkinResultCraft(List<SkinExterior> skinsNextRarity) {
        List<Tuple<Skin, Skin>> minAndMaxExteriorSkins = new ArrayList<>(); // _1 min _2 max
        Map<Long, List<Skin>> skinsSeparatedByWeaponId = new HashMap<>();// long = weapon id
        Set<Skin> skinsResult = new HashSet<>();

        skinsNextRarity.forEach(skinExterior -> {
            skinExterior.getSkins(contract.getSkin1().getQuality().getId() == 1).forEach(skin -> {
                if (skinsSeparatedByWeaponId.containsKey(skin.getWeapon().getId())) {
                    skinsSeparatedByWeaponId.get(skin.getWeapon().getId()).add(skin);
                } else {
                    skinsSeparatedByWeaponId.put(skin.getWeapon().getId(), new ArrayList<>(Collections.singleton(skin)));
                }
            });
        });

        skinsSeparatedByWeaponId.entrySet().forEach(skins -> {
            Tuple<Skin, Skin> minAndMaxExteriorSkin = new Tuple<>(null, null);

            minAndMaxExteriorSkin._1 = skins.getValue().stream().min(Comparator.comparingDouble(skin -> skin.getExterior().getFloatExterior())).orElse(null);
            minAndMaxExteriorSkin._2 = skins.getValue().stream().max(Comparator.comparingDouble(skin -> skin.getExterior().getFloatExterior())).orElse(null);

            minAndMaxExteriorSkins.add(minAndMaxExteriorSkin);
        });

        minAndMaxExteriorSkins.forEach(skinTuple -> {
            ExteriorType exteriorResult = getExteriorResult(averageFloatContract * getCoefficientRange(skinTuple) + skinTuple._1.getExterior().getFloatExterior());
            skinsResult.add(skinsSeparatedByWeaponId.get(skinTuple._1.getWeapon().getId()).stream().filter(skin -> skin.getExterior().getId().equals(exteriorResult.valueOfIdInBase()))
                    .findFirst().orElse(
                            skinsSeparatedByWeaponId.get(skinTuple._1.getWeapon().getId()).stream().findFirst().get()
                    )
            );
        });
        return skinsResult;
    }

    private void setChanceDropSkin(Set<Skin> skinsResult) {
        Map<Long, Long> numberCollections = new HashMap<>();
        joinSkinInContract().forEach(skin -> {
            Long idCollection = skin.getWeapon().getCollection().getId();
            if (numberCollections.containsKey(idCollection)) {
                numberCollections.put(idCollection, numberCollections.get(idCollection) + 1);
            } else {
                numberCollections.put(idCollection, 1L);
            }
        });
        int totalNumberOfProbabilities = numberCollections.entrySet().stream().mapToInt(numOfSkinsPerCollection ->
                        (int) (numOfSkinsPerCollection.getValue() * skinsResult.stream().filter(skin ->
                                skin.getWeapon().getCollection().getId().equals(numOfSkinsPerCollection.getKey())).count()))
                .reduce(0, Integer::sum);

        numberCollections.entrySet().forEach(numOfSkinsPerCollection ->
                skinsResult.stream().filter(skin -> skin.getWeapon().getCollection().getId().equals(numOfSkinsPerCollection.getKey())).forEach(skin -> {
                    skinsResultCraft.add(new Tuple<>((numOfSkinsPerCollection.getValue() / (double) totalNumberOfProbabilities), skin));
                })
        );
    }

    private double getCoefficientRange(Tuple<Skin, Skin> minAndMaxSkinsExteriorRange) {
        return getExteriorById(minAndMaxSkinsExteriorRange._2.getExterior().getId()).valueOfFloatRange()[1] - minAndMaxSkinsExteriorRange._1.getExterior().getFloatExterior() - 0.00001;
    }

    private void setAverageFloatContract() {
        averageFloatContract = joinSkinInContract().stream()
                .mapToDouble(skin -> getExteriorById(skin.getExterior().getId()).valueOfAverageFloat())
                .reduce(0d, Double::sum) / 10.0;
    }

    private ExteriorType getExteriorResult(double floatSkin) {
        for (ExteriorType exteriorType : ExteriorType.values()) {
            if (exteriorType.valueOfFloatRange()[0] <= floatSkin && floatSkin <= exteriorType.valueOfFloatRange()[1]) {
                return exteriorType;
            }
        }
        return ExteriorType.BATTLE_SCARRED;
    }

    private ExteriorType getExteriorById(long idExterior) {
        if (idExterior == 4) {
            return ExteriorType.FACTORY_NEW;
        } else if (idExterior == 5) {
            return ExteriorType.MINIMAL_WEAR;
        } else if (idExterior == 2) {
            return ExteriorType.FIELD_TESTED;
        } else if (idExterior == 3) {
            return ExteriorType.WELL_WORN;
        }
        return ExteriorType.BATTLE_SCARRED;
    }

    private RarityType getNextRarity() {
        long idCurrentRarity = contract.getSkin1().getWeapon().getRarity().getId();
        if (idCurrentRarity == 5) {
            return RarityType.INDUSTRIAL_GRADE;
        } else if (idCurrentRarity == 1) {
            return RarityType.MIL_SPEC_GRADE;
        } else if (idCurrentRarity == 6) {
            return RarityType.RESTRICTED;
        } else if (idCurrentRarity == 2) {
            return RarityType.CLASSIFIED;
        } else if (idCurrentRarity == 4) {
            return RarityType.COVERT;
        }
        return RarityType.CONSUMER_GRADE;
    }

    private double getPrice() {
        return joinSkinInContract().stream().map(Skin::getPrice).reduce(0d, Double::sum);
    }

    private double getCoefficient() {
        return skinsResultCraft.stream().mapToDouble(skin -> skin._1 * skin._2.getPrice()).sum()
                / contract.getPrice();
    }

    private void setSkinsContract(List<Skin> skinsToContract) {
        contract.setSkin1(skinsToContract.get(0));
        contract.setSkin2(skinsToContract.get(1));
        contract.setSkin3(skinsToContract.get(2));
        contract.setSkin4(skinsToContract.get(3));
        contract.setSkin5(skinsToContract.get(4));
        contract.setSkin6(skinsToContract.get(5));
        contract.setSkin7(skinsToContract.get(6));
        contract.setSkin8(skinsToContract.get(7));
        contract.setSkin9(skinsToContract.get(8));
        contract.setSkin10(skinsToContract.get(9));
    }

    private List<Skin> joinSkinInContract() {
        return List.of(
                contract.getSkin1(),
                contract.getSkin2(),
                contract.getSkin3(),
                contract.getSkin4(),
                contract.getSkin5(),
                contract.getSkin6(),
                contract.getSkin7(),
                contract.getSkin8(),
                contract.getSkin9(),
                contract.getSkin10()
        );
    }

    @Override
    public String toString() {
        return "Craft{" +
                "skinsResultCraft=" + skinsResultCraft +
                ", contract=" + contract +
                ", averageFloatContract=" + averageFloatContract +
                '}';
    }
}

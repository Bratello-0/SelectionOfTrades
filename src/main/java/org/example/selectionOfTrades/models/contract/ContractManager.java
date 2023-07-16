package org.example.selectionOfTrades.models.contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.enums.ExteriorType;
import org.example.selectionOfTrades.enums.RarityType;
import org.example.selectionOfTrades.models.contract.sorted.SkinsCollection;
import org.example.selectionOfTrades.models.contract.sorted.SkinsRarity;
import org.example.selectionOfTrades.models.entities.contractCSGO.Contract;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Skin;
import org.example.selectionOfTrades.models.tuple.Tuple;
import org.example.selectionOfTrades.services.attributes.CollectionService;
import org.example.selectionOfTrades.services.gunSkinsCSGO.SkinService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContractManager {

    private final SkinService skinService;
    private final CollectionService collectionService;
    private final SortSkinManeger sortSkinManeger;

    public List<Contract> newContracts() {
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll(createContract(sortSkin(), false));
        contracts.addAll(createContract(sortSkin(), true));
        return contracts;
    }

    private List<Contract> createContract(Map<Long, SkinsCollection> skinsCollectionMap, boolean isStatTrak) {
        List<Contract> contracts = new ArrayList<>();

        skinsCollectionMap.entrySet().stream().filter(skinsStatTrack -> skinsStatTrack.getValue().getIsStatTrak() || (!isStatTrak)).forEach(skinsCollection -> {
            skinsCollectionMap.entrySet().stream().filter(skinsStatTrack -> skinsStatTrack.getValue().getIsStatTrak() || (!isStatTrak)).skip(skinsCollection.getKey()).forEach(skinsCollectionEntry -> {

                for (RarityType rarityType : RarityType.values()) {
                    if (!rarityType.equals(RarityType.COVERT)) {

                        Tuple<SkinsRarity, SkinsRarity> raritySkins = new Tuple<>(
                                skinsCollection.getValue().getSkinsRarityMap().get(rarityType.valueOfIdInBase()),
                                skinsCollectionEntry.getValue().getSkinsRarityMap().get(rarityType.valueOfIdInBase()));

                        if (raritySkins._1.getCountSkinsRarity() > 0 && raritySkins._2.getCountSkinsRarity() > 0 &&
                                isNotNUllNextRarityCount(rarityType, skinsCollection.getValue(), skinsCollectionEntry.getValue())) {

                            swapSkinsCollectionTuple(raritySkins);
                            List<Tuple<Skin, Skin>> exteriorsSkins = joinSkinsExterior(raritySkins._1, raritySkins._2, isStatTrak);
                            if (exteriorsSkins == null) {
                                continue;
                            }
                            exteriorsSkins.forEach(exteriorSkins -> {
                                List<Contract> contractsToSave = new ArrayList<>();
                                Contract lastContract = null;
                                Contract currentContract = null;
                                for (int i = 1; i < 10; i++) {

                                    currentContract = new Contract();
                                    currentContract.setSkins(Arrays.asList(
                                                    new Tuple<>(10 - i, exteriorSkins._1),
                                                    new Tuple<>(i, exteriorSkins._2)
                                            )
                                    );
                                    if (lastContract != null) {
                                        if (new Craft(currentContract, skinsCollectionMap).getContract().getCoefficient() >
                                                new Craft(lastContract, skinsCollectionMap).getContract().getCoefficient()) {
                                            if (lastContract.getCoefficient() > 1) {
                                                contractsToSave.add(lastContract);
                                            }
                                            lastContract = currentContract;
                                            continue;
                                        } else {
                                            contractsToSave.add(currentContract);
                                            break;
                                        }
                                    }
                                    lastContract = currentContract;
                                }
                                if (contractsToSave.size() > 0) {
                                    contracts.addAll(contractsToSave);
                                }
                            });
                        }
                    }
                }
            });
        });
        return contracts;
    }



    private boolean isNotNUllNextRarityCount(RarityType rarityType, SkinsCollection skinsCollectionFirst, SkinsCollection skinsCollectionSecond) {
        if (skinsCollectionFirst.getSkinsRarityMap().get(rarityType.valueOfId()).getCountSkinsRarity() > 0 &&
                skinsCollectionSecond.getSkinsRarityMap().get(rarityType.valueOfId()).getCountSkinsRarity() > 0) {
            return true;
        }
        return false;
    }

    private void swapSkinsCollectionTuple(Tuple<SkinsRarity, SkinsRarity> raritySkins) {
        if (raritySkins._1.getMinExteriorSkinAllRarities(false).getPrice() >
                raritySkins._2.getMinExteriorSkinAllRarities(false).getPrice()) {
            SkinsRarity temp = raritySkins._1;
            raritySkins._1 = raritySkins._2;
            raritySkins._2 = temp;
        }
    }

    private List<Tuple<Skin, Skin>> joinSkinsExterior(SkinsRarity skinsRarity1, SkinsRarity skinsRarity2, boolean isStatTrak) {
        List<Tuple<Skin, Skin>> result = new ArrayList<>();
        for (ExteriorType exteriorType : ExteriorType.values()) {
            if (isStatTrak) {
                result.add(new Tuple<>(
                        skinsRarity1.getSkinsExteriorMap().get(exteriorType.valueOfIdInBase()).getSkinMinPriceStatTrak(),
                        skinsRarity2.getSkinsExteriorMap().get(exteriorType.valueOfIdInBase()).getSkinMinPriceStatTrak())
                );
            } else {
                result.add(new Tuple<>(
                        skinsRarity1.getSkinsExteriorMap().get(exteriorType.valueOfIdInBase()).getSkinMinPrice(),
                        skinsRarity2.getSkinsExteriorMap().get(exteriorType.valueOfIdInBase()).getSkinMinPrice())
                );
            }
        }

        List<Tuple<Skin, Skin>> resultsNull = result.stream().filter(resultTuple -> resultTuple._1 == null || resultTuple._2 == null).toList();
        if (resultsNull.size() > 0) {
            Tuple<Skin, Skin> nearestSkin = findNearestExterior(result);
            if ((nearestSkin._1 == null) || (nearestSkin._2 == null)) {
                return null;
            }
            resultsNull.forEach(resultNull -> {
                fillInNullSkinsExterior(resultNull, nearestSkin);
            });
        }
        return result.stream().filter(Objects::nonNull).toList();
    }

    private void fillInNullSkinsExterior(Tuple<Skin, Skin> resultNull, Tuple<Skin, Skin> notNull) {
        if (resultNull._1 == null) {
            resultNull._1 = notNull._1;
        }
        if (resultNull._2 == null) {
            resultNull._2 = notNull._2;
        }
    }

    private Tuple<Skin, Skin> findNearestExterior(List<Tuple<Skin, Skin>> result) {
        Tuple<Skin, Skin> skinTuple = new Tuple<>(null, null);
        skinTuple._1 = result.stream().filter(resultTuple -> resultTuple._1 != null)
                .min(Comparator.comparingDouble(skin -> skin._1.getExterior().getFloatExterior())).orElse(new Tuple<>(null, null))._1;

        skinTuple._2 = result.stream().filter(resultTuple -> resultTuple._2 != null)
                .min(Comparator.comparingDouble(skin -> skin._2.getExterior().getFloatExterior())).orElse(new Tuple<>(null, null))._2;
        return skinTuple;
    }

    public List<Contract> updateContracts(List<Contract> contractsOld) {
        Map<Long, SkinsCollection> skinsCollectionMap = sortSkin();
        List<Contract> newContracts = new ArrayList<>();
        contractsOld.forEach(contract -> {
            newContracts.add(new Craft(contract, skinsCollectionMap).getContract());
        });
        return newContracts;
    }

    private Map<Long, SkinsCollection> sortSkin() {
        sortSkinManeger.Sort(skinService.listSkins(null), collectionService.listCollection(null));
        return sortSkinManeger.getSortedSkinsMap();
    }
}

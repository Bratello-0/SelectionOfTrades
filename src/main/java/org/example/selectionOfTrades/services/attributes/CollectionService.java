package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Collection;
import org.example.selectionOfTrades.repositories.attributes.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public List<Collection> listCollection(String caseName) {
        if (caseName != null) collectionRepository.findByCaseName(caseName);
        return collectionRepository.findAll();
    }

    public Collection getCollection(String tag){
        return collectionRepository.findByTag(tag);
    }

    public void saveCollection(Collection collection) {
        printLog(collection, "Saving collection");
        collectionRepository.save(collection);
    }

    public void saveAllCollections(List<Collection> collectionList) {
        collectionList.forEach((collection) -> {
            printLog(collection, "Saving new");
        });
        collectionRepository.saveAll(collectionList);
    }

    public void saveAllCollections(Map<String, String> mapCollection) {
        if (mapCollection == null) {
            return;
        }
        List<Collection> collectionListToSave = new ArrayList<>();
        mapCollection.forEach((name, tag) -> {
            collectionListToSave.add(new Collection(name, tag));
        });
        if (collectionListToSave.size() != 0) {
            saveAllCollections(collectionListToSave);
        }
        log.info("###Save end Collections! count save : {}###", collectionListToSave.size());
    }

    private void printLog(Collection collection, String message) {
        log.info(String.join(" ", message, "name:{}; tag:{};"), collection.getCaseName(), collection.getTag());
    }
}

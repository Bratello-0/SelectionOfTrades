package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.services.gunSkinsCSGO.attributes.ExteriorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExteriorService {
    private final ExteriorRepository exteriorRepository;

    public List<Exterior> listExterior(String exterior) {
        if (exterior != null) exteriorRepository.findByExterior(exterior);
        return exteriorRepository.findAll();
    }

    public void saveExterior(Exterior exterior) {
        printLog(exterior, "Saving new");
        exteriorRepository.save(exterior);
    }

    public void deleteExterior(Long id) {
        exteriorRepository.deleteById(id);
    }

    public Exterior getExteriorId(Long id) {
        return exteriorRepository.findById(id).orElse(null);
    }

    public void saveAllExteriors(List<Exterior> exteriorList) {
        exteriorList.forEach(exterior -> {
            printLog(exterior, "Saving new");
        });
        exteriorRepository.saveAll(exteriorList);
    }

    public void saveAllExteriors(Map<String, String> mapExterior) {
        List<Exterior> exteriorListToSave = new ArrayList<>();
        mapExterior.forEach((name, tag) -> {
            if (!exteriorRepository.existsByExterior(name)) {
                exteriorListToSave.add(new Exterior(name, tag));
            }
        });

        if (exteriorListToSave.size() != 0) {
            saveAllExteriors(exteriorListToSave);
        }
    }


    private void printLog(Exterior exterior, String message) {
        log.info(String.join(" ", message, "name:{}; tag:{};"), exterior.getExterior(), exterior.getTag());
    }
}

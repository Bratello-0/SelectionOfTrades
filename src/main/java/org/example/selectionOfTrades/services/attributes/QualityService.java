package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.repositories.attributes.QualityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class QualityService {
    private final QualityRepository qualityRepository;

    public List<Quality> listQuality(String quality) {
        if (quality != null) qualityRepository.findByQuality(quality);
        return qualityRepository.findAll();
    }

    public void saveQuality(Quality quality) {
        printLog(quality, "Saving quality");
        qualityRepository.save(quality);
    }

    public void saveAllQualities(List<Quality> qualityList) {
        qualityList.forEach((quality) -> {
            printLog(quality, "Saving quality");
        });
        qualityRepository.saveAll(qualityList);
    }

    public void saveAllQualities(Map<String, String> mapQuality) {
        if(mapQuality== null){
            return;
        }
        List<Quality> qualityListToSave = new ArrayList<>();
        mapQuality.forEach((name, tag) -> qualityListToSave.add(new Quality(name, tag)));

        if (qualityListToSave.size() != 0) {
            saveAllQualities(qualityListToSave);
        }
    }


    private void printLog(Quality quality
            , String message) {
        log.info(String.join(" ", message, "name:{}; tag:{};"), quality.getQuality(), quality.getTag());
    }
}

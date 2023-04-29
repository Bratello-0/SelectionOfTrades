package org.example.selectionOfTrades.services.attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.skinCSGO.attributes.Quality;
import org.example.selectionOfTrades.repositories.skinCSGO.attributes.QualityRepository;
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
        printLog(quality, "Saving new");
        qualityRepository.save(quality);
    }

    public void saveAllQualities(List<Quality> qualityList) {
        qualityList.forEach((quality) -> {
            printLog(quality, "Saving new");
        });
        qualityRepository.saveAll(qualityList);
    }

    public void saveAllQualities(Map<String, String> mapQuality) {
        List<Quality> qualityListToSave = new ArrayList<>();
        mapQuality.forEach((name, tag) -> {
            if (!isExists(name)) {
                qualityListToSave.add(new Quality(name, tag));
            }
        });

        if (qualityListToSave.size() != 0) {
            saveAllQualities(qualityListToSave);
        }
    }

    public Boolean isExists(String quality) {
        if (qualityRepository.findByQuality(quality).size() == 0)
            return false;
        return true;
    }

    private void printLog(Quality quality
            , String message) {
        log.info(String.join(" ", message, "{}; {};"), quality.getQuality(), quality.getTag());
    }
}
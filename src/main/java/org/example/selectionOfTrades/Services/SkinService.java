package org.example.selectionOfTrades.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.skinCSGO.Skin;
import org.example.selectionOfTrades.repositories.SkinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkinService {
    private final SkinRepository skinRepository;

    public List<Skin> listSkins(Double price) {
        if(price != null) skinRepository.findByPrice(price);
        return skinRepository.findAll();
    }

    public void saveSkin(Skin skin) {
        log.info("Saving new {}", skin.getId());
        skinRepository.save(skin);
    }

    public void deleteSkin(Long id) {
        skinRepository.deleteById(id);
    }

    public Skin getSkinId(Long id) {
        return skinRepository.findById(id).orElse(null);
    }
}

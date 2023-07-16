package org.example.selectionOfTrades.models.parser.packeger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.selectionOfTrades.models.entities.attributes.Exterior;
import org.example.selectionOfTrades.models.entities.attributes.Quality;
import org.example.selectionOfTrades.models.entities.gunSkinCSGO.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonPackager implements JsonPackaged {

    private final Attributes attributes;
    @Autowired
    public ObjectMapper objectMapper;

    @Override
    public void jsonToAttributes(String jsonString) {
        try {
            attributes.join(objectMapper.readValue(jsonString, Attributes.class));
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DataSearch jsonToDataSearch(String jsonString){
        DataSearch dataSearch;
        try {
            dataSearch = objectMapper.readValue(jsonString, DataSearch.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return dataSearch;
    }
}
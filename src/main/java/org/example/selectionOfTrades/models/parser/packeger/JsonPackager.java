package org.example.selectionOfTrades.models.parser.packeger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.example.selectionOfTrades.interfaceModels.parser.packeger.JsonPackaged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JsonPackager implements JsonPackaged {

    @Autowired
    private ObjectMapper objectMapper;

    public Attributes stringToAttributes(String jsonString) {
        try {
            return  objectMapper.readValue(jsonString, Attributes.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

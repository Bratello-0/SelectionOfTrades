package org.example.selectionOfTrades.interfaceModels.parser.packeger;

import org.example.selectionOfTrades.models.parser.packeger.Attributes;

public interface JsonPackaged {
    Attributes stringToAttributes(String jsonString);
}

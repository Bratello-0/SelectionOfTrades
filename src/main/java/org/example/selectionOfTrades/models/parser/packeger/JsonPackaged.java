package org.example.selectionOfTrades.models.parser.packeger;

public interface JsonPackaged {
    void jsonToAttributes(String jsonString);
    DataSearch jsonToDataSearch(String jsonString);
}

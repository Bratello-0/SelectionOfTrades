package org.example.selectionOfTrades.enums;

public enum RarityType {
    COVERT("Covert", 3L, 5L, true),
    CLASSIFIED("Classified", 4L, 3L, true),
    RESTRICTED("Restricted", 2L, 4L, true),
    MIL_SPEC_GRADE("Mil-Spec Grade", 6L, 2L, true),
    INDUSTRIAL_GRADE("Industrial Grade", 1L, 6L, false),
    CONSUMER_GRADE("Consumer Grade", 5L, 1L, false);

    private final String name;
    private final Long id;
    private final Long idInBase;
    private final boolean possibleStatTrak;

    RarityType(String name, Long idInBase, Long id, boolean possibleStatTrak) {
        this.name = name;
        this.idInBase = idInBase;
        this.id = id;
        this.possibleStatTrak = possibleStatTrak;
    }

    public Long valueOfId() {
        return id;
    }

    public Long valueOfIdInBase() {
        return idInBase;
    }

    public String valueOfName() {
        return name;
    }

    public boolean valueOfStatTrak() {
        return possibleStatTrak;
    }
}

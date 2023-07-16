package org.example.selectionOfTrades.enums;

public enum ExteriorType {
    FACTORY_NEW("Factory New", 4L, 0L, 0.03501, new double[]{0.0, 0.07}),
    MINIMAL_WEAR("Minimal Wear", 5L, 1L, 0.11001, new double[]{0.07001, 0.15}),
    FIELD_TESTED("Field-Tested", 2L, 2L, 0.26501, new double[]{0.15001, 0.38}),
    WELL_WORN("Well-Worn", 3L, 3L, 0.41501, new double[]{0.38001, 0.45}),
    BATTLE_SCARRED("Battle-Scarred", 6L, 4L, 0.725005, new double[]{0.45001, 1.0});

    private final String name;
    private final Long id;
    private final Long idInBase;
    private final double averageFloat;
    private final double[] floatRange;

    ExteriorType(String name, Long idInBase, Long id, double averageFloat, double[] floatRange) {
        this.name = name;
        this.idInBase = idInBase;
        this.id = id;
        this.averageFloat = averageFloat;
        this.floatRange = floatRange;
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

    public double valueOfAverageFloat() {
        return averageFloat;
    }

    public double[] valueOfFloatRange() {
        return floatRange;
    }
}

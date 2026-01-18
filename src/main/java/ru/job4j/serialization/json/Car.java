package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean type;
    private final int doorCounter;
    private final String modelName;
    private final Engine engine;
    private final String[] equipments;

    public Car(boolean type, int doorCounter, String modelName, Engine engine, String[] equipments) {
        this.type = type;
        this.doorCounter = doorCounter;
        this.modelName = modelName;
        this.engine = engine;
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "Car{"
                + "type=" + type
                + ", doorCounter=" + doorCounter
                + ", modelName=" + modelName
                + ", engine" + engine
                + ", equipments" + Arrays.toString(equipments)
                + "}";
    }

}
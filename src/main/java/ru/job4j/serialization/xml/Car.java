package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean type;
    @XmlAttribute
    private int doorCounter;
    @XmlAttribute
    private String modelName;
    private Engine engine;
    @XmlElementWrapper(name = "equipments")
    @XmlElement(name = "equipment")
    private String[] equipments;

    public Car() { }

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

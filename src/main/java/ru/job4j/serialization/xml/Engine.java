package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Engine")
public class Engine {
    @XmlAttribute
    private int cylinders;
    @XmlAttribute
    private int power;

    public Engine() { }

    public Engine(int cylinders, int power) {
        this.cylinders = cylinders;
        this.power = power;
    }

    @Override
    public String toString() {
        return "{"
                + "cylinders=" + cylinders
                + ", power=" + power
                + "}";
    }
}

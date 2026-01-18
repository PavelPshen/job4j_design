package ru.job4j.serialization.json;

public class Engine {
    private final int cylinders;
    private final int power;

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
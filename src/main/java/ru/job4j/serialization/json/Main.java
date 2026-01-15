package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 5, "Wrangler", new Engine(4, 200),
                new String[] {"off-road kit", "air conditioner", "laser headlights"});

        final Gson gson = new GsonBuilder().create();
        final String gsonString = gson.toJson(car);
        System.out.println(gsonString);

        final  Car carFromGson = gson.fromJson(gsonString, Car.class);
        System.out.println(carFromGson);
    }
}

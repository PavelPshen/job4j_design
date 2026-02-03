package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JSONToPOJO {
        public static void main(String[] args) {
            final Car car = new Car(false, 5, "Wrangler",
                    new Engine(4, 200),
                    new String[] {"off-road kit", "air conditioner", "laser headlights"});

            /* JSONObject из json-строки для Engine */
            JSONObject jsonEngine = new JSONObject(
                    "{\"cylinders\":" + car.getEngine().getCylinders()
                            + ",\"power\":" + car.getEngine().getPower() + "}"
            );

            List<String> list = new ArrayList<>();
            list.add("off-road kit");
            list.add("air conditioner");
            list.add("laser headlights");
            JSONArray jsonEquipments = new JSONArray(list);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", car.getType());
            jsonObject.put("doorCounter", car.getDoorCounter());
            jsonObject.put("modelName", car.getModelName());
            jsonObject.put("engine", jsonEngine);
            jsonObject.put("equipments", jsonEquipments);

            System.out.println(jsonObject.toString());

            System.out.println(new JSONObject(car).toString());
        }
}

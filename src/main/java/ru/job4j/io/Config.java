package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            for (String line : reader.lines().toList()) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                String[] keyValue = line.split("=", 2);
                if (keyValue[0].equals(line)) {
                    throw new IllegalArgumentException("нет знака =");
                }
                if (keyValue[0].endsWith(" ") || keyValue[1].startsWith(" ")) {
                    throw new IllegalArgumentException("пробел возле знака =");
                }
                if (keyValue[0].isEmpty() || keyValue[1].isEmpty()) {
                    throw new IllegalArgumentException("нет ключа или значения");
                }
                values.put(keyValue[0], keyValue[1]);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        //throw new UnsupportedOperationException("Don't impl this method yet!");
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}
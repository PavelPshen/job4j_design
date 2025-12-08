package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    public static void argValidate(ArgsName argsName) {
        try {
            argsName.get("path");
        } catch (Exception e) {
            throw new IllegalArgumentException("ключ path отсутствует");
        }
        try {
            argsName.get("delimiter");
        } catch (Exception e) {
            throw new IllegalArgumentException("ключ delimiter отсутствует");
        }
        try {
            argsName.get("out");
        } catch (Exception e) {
            throw new IllegalArgumentException("ключ out отсутствует");
        }
        try {
            argsName.get("filter");
        } catch (Exception e) {
            throw new IllegalArgumentException("ключ filter отсутствует");
        }
        if (!new File(argsName.get("path")).exists()) {
            throw new IllegalArgumentException("входной файл не найден");
        }
        if ((!argsName.get("out").endsWith(".csv") && !argsName.get("out").equals("stdout"))) {
            throw new IllegalArgumentException("неудовлетворительные параметры вывода");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        PrintStream outputStream;
        if ("stdout".equals(argsName.get("out"))) {
            outputStream = System.out;
        } else {
            outputStream = new PrintStream(new FileOutputStream(argsName.get("out")));
        }
        String[] filter = argsName.get("filter").split(",");
        int[] indexData = new int[filter.length];
        Arrays.fill(indexData, -1);
        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                String[] fildsString = scanner.nextLine().split(delimiter);
                for (int i = 0; i < fildsString.length; i++) {
                    for (int j = 0; j < filter.length; j++) {
                        if (filter[j].equals(fildsString[i])) {
                            indexData[j] = i;
                        }
                    }
                }
                    if (Arrays.stream(indexData).anyMatch(value -> value == -1)) {
                        throw new IllegalArgumentException("ошибка в фильтре");
                    }
                StringJoiner joiner = new StringJoiner(delimiter);
                for (int index : indexData) {
                    if (index < fildsString.length) {
                        joiner.add(fildsString[index]);
                    } else {
                        joiner.add("");
                    }
                }
                outputStream.println(joiner);
            }
            outputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("Указаны не все аргументы");
        }
        ArgsName argsName = ArgsName.of(args);
        argValidate(argsName);
        handle(argsName);
    }
}
package ru.job4j.io;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        PrintStream outputStream;
        if ("stdout".equals(argsName.get("out"))) {
            outputStream = System.out;
        } else {
            outputStream = new PrintStream(new FileOutputStream(argsName.get("out")));
        }
        String[] filter = argsName.get("filter").split(",");
        int[] indexData = new int[filter.length];
        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                String[] fildsString = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < fildsString.length; i++) {
                    for (int j = 0; j < filter.length; j++) {
                        if (filter[j].equals(fildsString[i])) {
                            indexData[j] = i;
                        }
                    }
                }
                StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
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
        handle(argsName);
    }
}
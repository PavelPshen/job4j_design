package ru.job4j.io;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
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
        if (!args[0].startsWith("-path")) {
            throw new IllegalArgumentException("неверно обозначение первого входного параметра");
        }
        if (!args[1].startsWith("-delimiter")) {
            throw new IllegalArgumentException("неверно обозначение второго входного параметра");
        }
        if (!args[2].startsWith("-out")) {
            throw new IllegalArgumentException("неверно обозначение третьего входного параметра");
        }
        if (!args[3].startsWith("-filter")) {
            throw new IllegalArgumentException("неверно обозначение четвртого входного параметра");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataResult.txt")) {
            output.write("Hello, world!".getBytes());
            output.write(System.lineSeparator().getBytes());
            for (int x = 1; x < 11; x++) {
                for (int y = 1; y < 11; y++) {
                    output.write(String.format("%d * %d = %d", x, y, x * y).getBytes());
                    output.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public class Dir extends SimpleFileVisitor<Path> {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format(file.getAbsolutePath()));
        System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));
        for (File files : file.listFiles()) {
            PrintDirSize printDirSize = new PrintDirSize();
            System.out.print(files.getName() + " : ");
            Files.walkFileTree(Path.of(files.toString()), printDirSize);
            System.out.println(String.format(" %d байт", (long) printDirSize.getFolderSize()));
        }
    }
}

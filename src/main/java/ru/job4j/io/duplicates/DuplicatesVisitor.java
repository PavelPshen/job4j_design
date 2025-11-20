package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    // private Map<FileProperty, Path> mapPath = new HashMap<>();
    private Map<FileProperty, Set<String>> mapResult = new HashMap<>();

    public void printResult() {
        for (FileProperty duplicate : mapResult.keySet()) {
            if (mapResult.get(duplicate).size() > 1) {
                System.out.println(duplicate.getName() + " - " + duplicate.getSize() + " byte");
                for (String path : mapResult.get(duplicate)) {
                    System.out.println(path);
                }
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(),
                file.getFileName().toString());
        mapResult.computeIfAbsent(fileProperty, k -> new TreeSet<>())
                .add(file.toAbsolutePath().toString());
        return super.visitFile(file, attributes);
    }
}
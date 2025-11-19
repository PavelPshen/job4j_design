package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, Path> mapPath = new HashMap<>();
    private HashMap<FileProperty, TreeSet<String>> mapResult = new HashMap<>();

    public void printResult() {
        for (FileProperty duplicate : mapResult.keySet()) {
            System.out.println(duplicate.getName() + " - " + duplicate.getSize() + " byte");
            for (String path : mapResult.get(duplicate)) {
                System.out.println(path);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(),
                file.getFileName().toString());
            if (mapPath.containsKey(fileProperty)) {
                TreeSet<String> set = mapResult.getOrDefault(fileProperty, new TreeSet<>());
                set.add(mapPath.get(fileProperty).toAbsolutePath().toString());
                set.add(file.toAbsolutePath().toString());
                mapResult.put(fileProperty, set);
            } else {
                mapPath.put(fileProperty, file);
            }
        return super.visitFile(file, attributes);
    }
}
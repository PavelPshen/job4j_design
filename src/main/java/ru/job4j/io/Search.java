package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validTest(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validTest(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("args is null.");
        }
        if (!args[0].startsWith("C:")) {
            throw new IllegalArgumentException("Need disk C args");
        }
        if (!args[1].contains(".")) {
            throw new IllegalArgumentException("Need format file with '.' ");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
package ru.job4j.io.finder;

import ru.job4j.io.ArgsName;
import static ru.job4j.io.Search.search;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;

public class FileFinder {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Right format: -d=directory -n=filename -t=typeSearch -o=result");
        }
        ArgsName argsName = ArgsName.of(args);
        Path startDir = Paths.get(argsName.get("d"));
        String pattern = argsName.get("n");
        String type = argsName.get("t");
        PathMatcher matcher = null;
        PrintStream outputStream = new PrintStream(new FileOutputStream(argsName.get("o")));
        switch (type) {
            case "name" : search(startDir, path -> path.toFile()
                    .getName()
                    .endsWith(pattern)).
                    forEach(outputStream::println);
                outputStream.close();
            break;
            case "mask" : matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:**/" + pattern);
            break;
            case "regex" : matcher = FileSystems.getDefault()
                    .getPathMatcher("regex:" + pattern);
            break;
            default : System.out.println("Не верный тип поиска"); break;
        }
        if (matcher != null) {
            try {
                Files.walk(Path.of(String.valueOf(startDir)))
                        .filter(Files::isRegularFile)
                        .filter(matcher::matches)
                        .forEach(outputStream::println);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

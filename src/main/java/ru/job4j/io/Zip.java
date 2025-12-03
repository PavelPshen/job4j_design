package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validArgs(String[] args) {

        if (args.length < 3) {
            throw new IllegalArgumentException("Указаны не все аргументы");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output  = argsName.get("o");
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException("Директории не существует");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("не указан формат файлов");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("не верный формат архива");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream outZip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                outZip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream inputZip =
                             new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                    outZip.write(inputZip.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validArgs(args);
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.packFiles(
                Search.search(Paths.get(argsName.get("d")),
                        path -> !path.toFile().getName().endsWith(argsName.get("e"))),
                new File(argsName.get("o"))
        );
    }
}
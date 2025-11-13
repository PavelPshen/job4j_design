package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintDirSize extends SimpleFileVisitor<Path> {
    private long folderSize;

    public double getFolderSize() {
        return folderSize;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        folderSize += file.toFile().length();
        return super.visitFile(file, attributes);
    }
}


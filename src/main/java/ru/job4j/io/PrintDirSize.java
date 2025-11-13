package ru.job4j.io;

import ru.job4j.io.duplicates.FileProperty;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintDirSize extends SimpleFileVisitor<Path> {
    long folderSize;

    public double getFolderSize() {
        return folderSize;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.toString());
        folderSize += fileProperty.getSize();
        return super.visitFile(file, attributes);
    }
}


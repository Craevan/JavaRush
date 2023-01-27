package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    private Path path;

    public FileBucket() throws IOException {
        this.path = Files.createTempFile(null, null);
        path.toFile().deleteOnExit();
        Files.deleteIfExists(path);
        Files.createFile(path);
    }

    public long getFileSize() throws IOException {
        return Files.size(path);
    }

    public void putEntry(Entry entry) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
        oos.writeObject(entry);
    }

    public Entry getEntry() throws IOException, ClassNotFoundException {
        if (getFileSize() == 0) {
            return null;
        }
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
        return (Entry) ois.readObject();
    }

    public void remove() throws IOException {
        Files.delete(path);
    }
}

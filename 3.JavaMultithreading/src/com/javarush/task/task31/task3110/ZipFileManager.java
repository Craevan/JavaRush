package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private final Path zipFile;

    public ZipFileManager(final Path zip) {
        this.zipFile = zip;
    }

    public void createZip(final Path source) throws Exception {
        /*
        //создаем новый поток архива
        try(ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            //получаем имя файла из пути
            String fileName = source.getFileName().toString();
            // Создаем новую сущность Entry (элемент архива)
            ZipEntry zEntry = new ZipEntry(fileName);
            //Кладем элемент архива в архив
            zos.putNextEntry(zEntry);
            //Переписываем данные из файла в поток архива
                //создаем поток InputStream
            try (InputStream is = Files.newInputStream(source)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            // Закрываем элемент архива у потока
            zos.closeEntry();

         */

        if (Files.notExists(zipFile.getParent())) {
            Files.createDirectory(zipFile.getParent());
        }

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            if (Files.isRegularFile(source))
                addNewZipEntry(zos, source.getParent(), source.getFileName());
            else if (Files.isDirectory(source)) {
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();
                for (Path p : fileNames) {
                    addNewZipEntry(zos, source, p);
                }
            } else throw new PathIsNotFoundException();
        }

    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        try (InputStream is = Files.newInputStream(filePath.resolve(fileName))) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);
            copyData(is, zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    }

}

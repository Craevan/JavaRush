package com.javarush.task.task31.task3110;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private final Path zipFile;

    public ZipFileManager(final Path zip) {
        this.zipFile = zip;
    }

    public void createZip(final Path source) throws Exception {
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
        }

    }
}

package com.javarush.task.task31.task3105;

import javax.swing.text.AbstractDocument;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        
        List<Content> content = getContent(args[1]);
        
        FileOutputStream zipFile = new FileOutputStream(args[1]);
        ZipOutputStream zip = new ZipOutputStream(zipFile);
        
        File file = new File(args[0]);
        zip.putNextEntry(new ZipEntry("new/" + file.getName()));
        Files.copy(file.toPath(), zip);
        
        for (Content tmp : content) {
            if (!tmp.getFileName().equals("new/" + file.getName()))
                tmp.saveToZip(zip);
        }
        
        zip.close();
    }
    
    private static List<Content> getContent(String args) throws IOException {
        List<Content> content = new ArrayList<>();
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(args))) {
            ZipEntry currentEntry;
            byte[] buffer = new byte[1024];
            while((currentEntry = zis.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = zis.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                content.add(new Content(currentEntry.getName(), outputStream.toByteArray()));
            }
        }
        
        return content;
    }
    
    static class Content {
        String fileName;
        byte[] data;

        public Content(String fileName, byte[] data) {
            this.fileName = fileName;
            this.data = data;
        }

        public String getFileName() {
            return fileName;
        }

        public byte[] getData() {
            return data;
        }

        void saveToZip(ZipOutputStream zip) throws IOException {
            ZipEntry zipEntry = new ZipEntry(getFileName());
            zip.putNextEntry(zipEntry);
            zip.write(getData());
            zip.closeEntry();
        }
    }
}

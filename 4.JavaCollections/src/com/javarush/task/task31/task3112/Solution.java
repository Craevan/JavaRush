package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("/home/user/Downloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        String[] tmp = urlString.split("/");
        String fileName = tmp[tmp.length - 1];

        Path downloadPath = downloadDirectory.resolve(fileName);
        String s = downloadPath.toString();
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        Path tempFile = Files.createTempFile("JR_", ".tmp");
        Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
        is.close();
        Files.move(tempFile, downloadPath);
        return downloadPath;
    }
}

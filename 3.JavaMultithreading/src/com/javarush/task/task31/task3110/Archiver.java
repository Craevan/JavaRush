package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {

    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к архиву");
        String archivePath = consoleReader.readLine();
        ZipFileManager zfm = new ZipFileManager(Paths.get(archivePath));
        System.out.println("Введите путь к файлу");
        String filePath = consoleReader.readLine();
        consoleReader.close();
        zfm.createZip(Paths.get(filePath));

        new ExitCommand().execute();
    }

}

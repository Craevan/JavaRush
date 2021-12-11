package com.javarush.task.pro.task15.task1504;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);
        String filePathFirst = scanner.nextLine();
        String filePathSecond = scanner.nextLine();

        try(scanner; InputStream in = Files.newInputStream(Path.of(filePathFirst));
            OutputStream out = Files.newOutputStream(Path.of(filePathSecond))) {
            while (in.available() != 0) {
                int firstByte = in.read();
                if (in.available() != 0) {
                    int secondByte = in.read();
                    out.write(secondByte);
                }
                out.write(firstByte);
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}


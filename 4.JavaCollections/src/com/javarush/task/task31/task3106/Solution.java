package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        File result = new File(args[0]);
        if (!result.exists())
            result.createNewFile();

        List<FileInputStream> fileInputStreams = new ArrayList<>();
        List<String> filenames = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        Collections.sort(filenames);

        for (String name : filenames)
            fileInputStreams.add(new FileInputStream(name));

        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreams)))) {
            while (true) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                if (zipEntry == null)
                    break;
                try (OutputStream os = new BufferedOutputStream(new FileOutputStream(result))) {
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int bytes; (bytes = zipInputStream.read(buffer, 0, bufferSize)) > -1; ) {
                        os.write(buffer, 0, bytes);
                    }
                    os.flush();


                }
            }
        }
    }
}

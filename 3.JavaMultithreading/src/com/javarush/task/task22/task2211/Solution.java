package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(args[0]), Charset.forName("Windows-1251"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)) {
            while (in.ready()) {
                out.write(in.read());
            }
        }
    }
}

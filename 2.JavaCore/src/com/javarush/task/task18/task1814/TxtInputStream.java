package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.IOException;


/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {

    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        if (verify(fileName)) {
            super(new);
        }
        else {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    private boolean verify(String text) {
        String[] strings = text.split("\\.");
        return "txt".equalsIgnoreCase(strings[strings.length - 1]);
    }

    public static void main(String[] args) {
    }
}


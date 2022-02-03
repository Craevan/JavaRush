package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

    private FileOutputStream orig;

    public AmigoOutputStream(FileOutputStream fos) throws FileNotFoundException {
        super(fileName);
        this.orig = fos;
    }

    @Override
    public void close() throws IOException {
        String end = "JavaRush © All rights reserved.";
        orig.flush();
        orig.write(end.getBytes());
        orig.close();
    }

    @Override
    public void write(int b) throws IOException {
        orig.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        orig.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        orig.write(b, off, len);
    }

    @Override
    public FileChannel getChannel() {
        return orig.getChannel();
    }

    @Override
    public void flush() throws IOException {
        orig.flush();
    }
}

package com.javarush.task.task31.task3109;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        Path path = Paths.get(fileName);

        try (InputStream is = new FileInputStream(fileName)) {
            String extension = getFileExtension(path);
            if ("xml".equals(extension))
                properties.loadFromXML(is);
            else 
                properties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
    
    private String getFileExtension(Path path) {
        String s = path.toAbsolutePath().toString();

        int index = s.lastIndexOf(File.separator);
        if (index >= 0) {
            int i = s.lastIndexOf(".");
            if (i > index)
                return s.substring(i + 1);
        }

        /*
        int index = s.lastIndexOf(".");
        s = s.substring(index + 1);
         */
        return s;
    }
}

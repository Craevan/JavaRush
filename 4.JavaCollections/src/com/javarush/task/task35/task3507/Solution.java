package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) {
            pathToAnimals = pathToAnimals + "/";
        }
        File dir = new File(pathToAnimals);
        String[] patches = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.toLowerCase().endsWith(".class");
            }
        });
        for (String s : patches) {
            try {
                final String finalPathToAnimals = pathToAnimals;
                boolean hasInterface = false;
                boolean hasConstructor = false;
                
                ClassLoader loader = new ClassLoader() {
                    @Override
                    public Class<?> findClass(String name) throws ClassNotFoundException {
                        try {
                            byte[] bytes = fetchClassFromFS(finalPathToAnimals + name + ".class");
                            return defineClass(null, bytes, 0, bytes.length);
                        } catch (FileNotFoundException fnfe) {
                            return super.findClass(name);
                        } catch (IOException ioe) {
                            return super.findClass(name);
                        }
                    }
                };
                String className = s.substring(0, s.length() - 6);
                Class clazz  = loader.loadClass(className);
                
                Class[] interfaces = clazz.getInterfaces();
                for (Class c : interfaces) {
                    if (Animal.class == c) {
                        hasInterface = true;
                        break;
                    }
                }
                if (!hasInterface) {
                    continue;
                }
                
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor c : constructors) {
                    if (c.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                }
                if (!hasConstructor) {
                    continue;
                }
                result.add((Animal) clazz.newInstance());
            } catch (Exception ex) {
                
            }
        }
        return result;
    }
    
    private static byte[] fetchClassFromFS(String path) throws IOException {
        InputStream is = new FileInputStream(new File(path));
        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
            
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                    && (numRead = is.read(bytes,offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException();
        }
        is.close();
        return bytes;
        
    }
}

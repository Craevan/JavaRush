package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user_1 = new User();
            user_1.setFirstName("Alex");
            user_1.setLastName("Ivanov");
            user_1.setBirthDate(new Date(100000));
            user_1.setMale(true);
            user_1.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user_1);

            User user_2 = new User();
            user_2.setFirstName("Ivan");
            user_2.setLastName("Petrov");
            user_2.setBirthDate(new Date(300000));
            user_2.setMale(true);
            user_2.setCountry(User.Country.UKRAINE);

            javaRush.users.add(user_2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(loadedObject.equals(javaRush));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            String separator = "~~~~~~~~~~~~~~~~~~~~";
            PrintWriter writer = new PrintWriter(outputStream);
            if (this.users.size() != 0) {
                for (User u : this.users) {
                    writer.println(u.getFirstName());
                    writer.println(u.getLastName());
                    writer.println(u.getCountry());
                    writer.println(u.getBirthDate().getTime());
                    writer.println(u.isMale() ? "Male" : "Female");
                    writer.println(separator);
                }
            }
            writer.close();

        }



        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                User user = new User();
                String s;
                String[] strings = new String[5];
                for (int i = 0;; i++) {
                    s = reader.readLine();
                    if (s == null)
                        break;
                    else if (s.equals("~~~~~~~~~~~~~~~~~~~~")) {
                        i = -1;
                        user.setFirstName(strings[0]);
                        user.setLastName(strings[1]);
                        switch (strings[2]) {
                            case "RUSSIA":
                                user.setCountry(User.Country.RUSSIA);
                                break;
                            case "UKRAINE":
                                user.setCountry(User.Country.UKRAINE);
                                break;
                            default:
                                user.setCountry(User.Country.OTHER);
                                break;
                        }
                        user.setBirthDate(new Date(Long.parseLong(strings[3])));
                        user.setMale(strings[4].equals("Male"));
                        this.users.add(user);
                        user = new User();
                    }
                    else {
                        strings[i] = s;
                    }
                }

            reader.close();

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}

package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //напишите тут ваш код
        switch (args[0]) {
            case ("-c") :
                create(args[1], args[2], args[3]);
                break;
            case ("-r") :
                break;
            case ("-u") :
                break;
            case ("-d") :
                break;
        }

    }

    private static void create(String name, String sex, String date) {

        Date bd = null;
        try {
            bd = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Person person = null;
        switch (sex) {
            case ("м") :
                person = Person.createMale(name, bd);
                break;
            case ("ж") :
                person = Person.createFemale(name, bd);
                break;
        }
        if (person != null)
            allPeople.add(person);
        System.out.println(allPeople.indexOf(person));

    }

    private static void update(int id, String name, String sex, String date) {
        Date bd = null;
        try {
            bd = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person person = allPeople.get(id);
        person.setName(name);
        person.setBirthDate(bd);
        if (sex.equalsIgnoreCase("м"))
            person.setSex(Sex.MALE);
        if (sex.equalsIgnoreCase("ж"))
            person.setSex(Sex.FEMALE);

        allPeople.set(id, person);


    }
}

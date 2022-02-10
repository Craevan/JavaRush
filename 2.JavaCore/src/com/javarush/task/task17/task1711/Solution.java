package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat SDF_OUT = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        switch (args[0]) {
            case ("-c") :
                synchronized (allPeople) {
                    create(args);
                    break;
                }
            case ("-i") :
                synchronized (allPeople) {
                    read(args);
                    break;
                }
            case ("-u") :
                synchronized (allPeople) {
                    update(args);
                    break;
                }
            case ("-d") :
                synchronized (allPeople) {
                    delete(args);
                    break;
                }
        }
    }

    private static void create(String[] params) throws ParseException {
        Person person;
        for (int i = 1; i < params.length; i += 3) {
            if (params[i + 1].equalsIgnoreCase("м"))
                person = Person.createMale(params[i], SDF.parse(params[i + 2]));
            else
                person = Person.createFemale(params[i], SDF.parse(params[i + 2]));

            allPeople.add(person);
            System.out.println(allPeople.size() - 1);
        }
    }

    private static void read(String[] params) {
        Person person;
        String gender;
        for (int i = 1; i < params.length; i++) {
            int index = Integer.parseInt(params[i]);
            person = allPeople.get(index);
            gender = person.getSex() == Sex.MALE ? "м" : "ж";
            System.out.println(person.getName() + " " + gender + " " + SDF_OUT.format(person.getBirthDate()));
        }

    }

    private static void update(String[] params) throws ParseException {
        Person person;
        for (int i = 1; i < params.length; i += 4) {
            int index = Integer.parseInt(params[i]);
            person = allPeople.get(index);
            person.setName(params[i + 1]);
            person.setSex(params[i + 2].equalsIgnoreCase("м") ? Sex.MALE : Sex.FEMALE);
            person.setBirthDate(SDF.parse(params[i + 3]));
            allPeople.set(index, person);
        }
    }

    private static void delete(String[] params) {
        Person person;
        for (int i = 1; i < params.length; i++) {
            int index = Integer.parseInt(params[i]);
            person = allPeople.get(index);
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
            allPeople.set(index, person);
        }
    }

}

package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;



    public University(String name, int age) {
        setName(name);
        setAge(age);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double tmp = 0.0;
        for (Student student : students) {
            if (student.getAverageGrade() > tmp)
                tmp = student.getAverageGrade();
        }
        for (Student student : students) {
            if (student.getAverageGrade() == tmp)
                return student;
        }
        return null;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        double tmp = Double.MAX_VALUE;
        for (Student student : students) {
            if (student.getAverageGrade() < tmp)
                tmp = student.getAverageGrade();
        }
        for (Student student : students) {
            if (student.getAverageGrade() == tmp)
                return student;
        }
        return null;
    }

    public void expel(Student student) {
        students.remove(student);
    }

}
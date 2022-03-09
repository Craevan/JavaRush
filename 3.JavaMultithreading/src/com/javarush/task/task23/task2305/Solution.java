package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        Solution sol_1 = new Solution();
        sol_1.innerClasses[0] = sol_1.new InnerClass();
        sol_1.innerClasses[1] = sol_1.new InnerClass();
        Solution sol_2 = new Solution();
        sol_2.innerClasses[0] = sol_2.new InnerClass();
        sol_2.innerClasses[1] = sol_2.new InnerClass();
        solutions[0] = sol_1;
        solutions[1] = sol_2;

        return solutions;
    }

    public static void main(String[] args) {

    }
}

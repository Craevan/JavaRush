package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/* 
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef

        number = new Number(NumberSystemType._2, "120");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        //напишите тут ваш код
        String strDigit = number.getDigit();
        if (strDigit.charAt(0) != '-' && !strDigit.contains(".")) {
            int currentBase = number.getNumberSystem().getNumberSystemIntValue();
//            try {
                BigInteger bValue = new BigInteger(number.getDigit(), currentBase);
                String strResult = bValue.toString(expectedNumberSystem.getNumberSystemIntValue());
                return new Number(expectedNumberSystem, strResult);
//            } catch (NumberFormatException nfe) {
//                throw nfe;
//            }
        }
        return null;
    }
}

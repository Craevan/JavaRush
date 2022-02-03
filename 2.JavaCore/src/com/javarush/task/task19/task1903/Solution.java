package com.javarush.task.task19.task1903;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Адаптация нескольких интерфейсов
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {

        private final IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getName() {
            return this.data.getContactLastName().concat(", ")
                                                 .concat(this.data.getContactFirstName());
        }

        @Override
        public String getPhoneNumber() {
            char[] phoneNumber = new char[14];
            char[] phoneNumberDigits = String.valueOf(this.data.getPhoneNumber()).toCharArray();
            char[] tenDigits = new char[10];
            int size = phoneNumberDigits.length;
            if (size - 10 < 0) {
                Arrays.fill(tenDigits, 0, 10 - size, '0');
                System.arraycopy(phoneNumberDigits, 0, tenDigits, 10 - size, phoneNumberDigits.length);
            }
            else {
                System.arraycopy(phoneNumberDigits, 0, tenDigits, 0, phoneNumberDigits.length);
            }

            for (int i = 0, j = 0; i < phoneNumber.length; i++) {
                if (i == 0) {
                    phoneNumber[i] = '(';
                    continue;
                }
                if (i == 4) {
                    phoneNumber[i] = ')';
                    continue;
                }
                if (i == 8 || i == 11) {
                    phoneNumber[i] = '-';
                    continue;
                }
                phoneNumber[i] = tenDigits[j];
                ++j;
            }

            String res = "";
            for (char c : phoneNumber) {
                res = res.concat(String.valueOf(c));

            }

            return "+".concat(String.valueOf(this.data.getCountryPhoneCode()))
                      .concat(res);
        }

        @Override
        public String getCompanyName() {
            return this.data.getCompany();
        }

        @Override
        public String getCountryName() {
            String code = this.data.getCountryCode();
            return countries.get(code);
        }
    }


    public interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example1: 501234567, For example2: 71112233
    }

    public interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example1: +38(050)123-45-67, For example2: +38(007)111-22-33
    }
}
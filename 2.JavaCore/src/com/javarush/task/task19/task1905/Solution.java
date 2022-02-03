package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
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

    public static class DataAdapter implements RowItem {

        private final Customer customer;
        private final Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String name = null;
            for (Map.Entry<String, String> entry : countries.entrySet()) {
                if (this.customer.getCountryName().equalsIgnoreCase(entry.getValue()))
                    name = entry.getKey();
            }
            return name;
        }

        @Override
        public String getCompany() {
            return this.customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] names = this.contact.getName().split(", ");
            return names[1];
        }

        @Override
        public String getContactLastName() {
            String[] names = this.contact.getName().split(", ");
            return names[0];
        }

        @Override
        public String getDialString() {
            StringBuilder res = new StringBuilder("callto://+");
            char[] chars = this.contact.getPhoneNumber().toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c))
                    res.append(c);
            }
            return res.toString();
        }

    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}
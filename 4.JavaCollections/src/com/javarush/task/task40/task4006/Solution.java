package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try (Socket socket = new Socket(url.getHost(), 80);
             PrintStream ps = new PrintStream(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String responseLine;
            ps.println("GET " + url.getPath());
            ps.println("Host: www." + url.getHost());
            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

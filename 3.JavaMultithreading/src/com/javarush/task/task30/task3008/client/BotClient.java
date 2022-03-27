package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random() * 100);
    }

    public static void main(String[] args) {
        BotClient bot = new BotClient();
        bot.run();
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            String botMessage = "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.";
            BotClient.this.sendTextMessage(botMessage);
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] messageInfo = message.split(": ");
            if (messageInfo.length != 2)
                return;
            SimpleDateFormat sdf;
            String botMessage = "Информация для " + messageInfo[0] + ": ";
            switch (messageInfo[1]) {
                case ("дата"):
                    sdf = new SimpleDateFormat("d.MM.yyyy");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("день"):
                    sdf = new SimpleDateFormat("d");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("месяц"):
                    sdf = new SimpleDateFormat("MMMM");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("год"):
                    sdf = new SimpleDateFormat("yyyy");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("время"):
                    sdf = new SimpleDateFormat("H:mm:ss");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("час"):
                    sdf = new SimpleDateFormat("H");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("минуты"):
                    sdf = new SimpleDateFormat("m");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
                case ("секунды"):
                    sdf = new SimpleDateFormat("s");
                    sendTextMessage(botMessage + sdf.format(Calendar.getInstance().getTime()));
                    break;
            }
        }
    }

}

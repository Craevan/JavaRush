package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            this.connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Сообщение не отправлено");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try {
            synchronized (this) {
                wait();
            }
        }
        catch (InterruptedException ie) {
            ConsoleHelper.writeMessage("Ошибка ожидания потока");
            return;
        }

        if (clientConnected)
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

        while (clientConnected) {
            String message = ConsoleHelper.readString();
            if ("exit".equals(message))
                break;
            if (shouldSendTextFromConsole())
                sendTextMessage(message);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }


    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " Connected to chat");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " Disconnected from chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {
                Message messageFromSRV = connection.receive();

                if (messageFromSRV.getType() == MessageType.NAME_REQUEST) {
                    Message messageFromClient = new Message(MessageType.USER_NAME, Client.this.getUserName());
                    connection.send(messageFromClient);
                }
                else if (messageFromSRV.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                }
                else
                    throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message messageFromSRV = Client.this.connection.receive();

                if (messageFromSRV.getType() == MessageType.TEXT)
                    processIncomingMessage(messageFromSRV.getData());
                else if (messageFromSRV.getType() == MessageType.USER_ADDED)
                    informAboutAddingNewUser(messageFromSRV.getData());
                else if (messageFromSRV.getType() == MessageType.USER_REMOVED)
                    informAboutDeletingNewUser(messageFromSRV.getData());
                else
                    throw new IOException("Unexpected MessageType");
            }
        }

        public void run() {
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException | ClassNotFoundException exception) {
                notifyConnectionStatusChanged(false);
            }
        }

    }

}

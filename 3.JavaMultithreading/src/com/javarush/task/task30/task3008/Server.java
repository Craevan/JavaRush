package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket srvSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("SERVER STARTED");
            while (true) {
                Handler handler = new Handler(srvSocket.accept());
                handler.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            Connection connection = entry.getValue();
            try {
                connection.send(message);
            } catch (IOException e) {
                System.out.println("Сообщение не доставлено!");
            }
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        // Реализация протокола
        //Этап I - рукопожатие

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message receivedMessage = connection.receive();

                if (receivedMessage.getType() != MessageType.USER_NAME) {
                    continue;
                }
                if (receivedMessage.getData().isEmpty()) {
                    continue;
                }
                if (connectionMap.containsKey(receivedMessage.getData()))
                    continue;

                connectionMap.put(receivedMessage.getData(), connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return receivedMessage.getData();
            }
        }
        //Этап 2 - отправка информации клиентах, новому участнику

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                String clientName = entry.getKey();
                if (!clientName.equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, clientName);
                    connection.send(message);
                }
            }
        }

        // Этап третий - главный цикл обработки сообщений сервером.
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT) {
                    String textWithAuthor = userName + ": " + receivedMessage.getData();
                    Message messageWithAuthor = new Message(MessageType.TEXT, textWithAuthor);
                    sendBroadcastMessage(messageWithAuthor);
                }
                else {
                    ConsoleHelper.writeMessage("Incorrect message type");
                }
            }
        }

        // главный метод, который будет вызывать все вспомогательные методы.
        public void run() {
            ConsoleHelper.writeMessage("Connection with " + socket.getRemoteSocketAddress() + " successful");
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException | ClassNotFoundException ioe) {
                ConsoleHelper.writeMessage("Error");
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Connection with " + socket.getRemoteSocketAddress() + " closed");
        }
    }

}

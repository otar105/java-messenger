package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


    public static ArrayList<Socket> socketList = new ArrayList<>();
    private ServerSocket serverSocket;
    private int numberOfUsers;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

        while (true) {
            Socket newUser = serverSocket.accept();
            socketList.add(newUser);

            new SendMessage(socketList.get(socketList.size() - 1), socketList).start();
        }

    }
}

package server;

import DB.User;
import server.ThreadServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    public static ArrayList<Socket> clients = new ArrayList<>();
    public static HashMap<Socket, User> SocketUserList;
    public static void main(String[] args) {
        try (ServerSocket serversocket = new ServerSocket(5000)) {
            System.out.println("Server is started...");
            while (true) {
                Socket socket = serversocket.accept();
                clients.add(socket);
                ThreadServer ThreadServer = new ThreadServer(socket, clients, SocketUserList);
                ThreadServer.start();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}

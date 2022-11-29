package server;

import DB.User;
import server.ThreadServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    public static void main(String[] args) {
        ArrayList<Socket> clients = new ArrayList<>();
        HashMap<Socket, User> SocketUserList = new HashMap<Socket, User>();
        try (ServerSocket serversocket = new ServerSocket(9999)) {
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

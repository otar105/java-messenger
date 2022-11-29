package server;


import DB.User;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadServer extends Thread {

    private Socket socket;
    private ArrayList<Socket> clients;
    private HashMap<Socket, User> socketClientList;

    public ThreadServer(Socket socket, ArrayList<Socket> clients, HashMap<Socket, User> socketClientList) {
        this.socket = socket;
        this.clients = clients;
        this.socketClientList = socketClientList;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String outputString = input.readLine();
                System.out.println("info:");
                System.out.println(outputString);
                if (outputString.equals("1")) {
                    PrintWriter printWriter;
                    for (Map.Entry<Socket, User> entry : socketClientList.entrySet()) {
                        Socket key = entry.getKey();
                        User value = entry.getValue();
                        System.out.println(value.getID() + " " + value.getEmail());
                    }
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(outputString);
                }
                String[] messageString = outputString.split(":",5);
                User currentUser = new User(messageString[1],messageString[2],messageString[3],Integer.parseInt(messageString[4]));
                System.out.println(currentUser.getEmail());
                currentUser.setID(Integer.parseInt(messageString[0]));
                if (!socketClientList.containsKey(socket)) {
                    socketClientList.put(socket, currentUser);
                    System.out.println(outputString);
                    showMessageToAllClients(socket, "otariiiii",currentUser);
                } else {
                    System.out.println(outputString);
                    showMessageToAllClients(socket, "otairiiiidf",currentUser);
                }
            }
        } catch (SocketException e) {
            String printMessage = socketClientList.get(socket) + " left the Server";
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    private void showMessageToAllClients(Socket sender, String outputString, User senderUser) {
        Socket socket;
        PrintWriter printWriter;
        int i = 0;
        while (i < clients.size()) {
            socket = clients.get(i);
            i++;
            try {
                if (socket != sender && socketClientList.get(socket).getCurrentchatid() == senderUser.getCurrentchatid()) {
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(outputString);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}

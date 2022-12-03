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
                String[] tmessageString = outputString.split(":",5);
                switch (tmessageString[1]) {
                    case "1": // show all online users
                        StringBuilder sb = new StringBuilder();
                        socketClientList.forEach((socket,user) -> {
                            sb.append(user.getEmail()).append(" | ");
                        });
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(sb.toString());
                        break;
                    case "2":
                        if (socketClientList.containsKey(socket)) {
                            String[] messageString = outputString.split(":",5);
                            String[] userStringElements = messageString[0].split(",");
                            User currentUser = new User(userStringElements[1],userStringElements[2],userStringElements[3],Integer.parseInt(userStringElements[4]));
                            socketClientList.get(socket).setCurrentchatid(currentUser.getCurrentchatid());
                            PrintWriter printWriter2 = new PrintWriter(socket.getOutputStream(), true);
                        } else{
                        String[] messageString = outputString.split(":",5);
                        String[] userStringElements = messageString[0].split(",");
                        User currentUser = new User(userStringElements[1],userStringElements[2],userStringElements[3],Integer.parseInt(userStringElements[4]));
                        socketClientList.get(socket).setCurrentchatid(currentUser.getCurrentchatid());
                        PrintWriter printWriter2 = new PrintWriter(socket.getOutputStream(), true);
                        }
                        break;
                    case "3":
                        if (socketClientList.containsKey(socket)) {
                            String[] messageString = outputString.split(":",5);
                            String[] userStringElements = messageString[0].split(",");
                            User currentUser = new User(userStringElements[1],userStringElements[2],userStringElements[3],Integer.parseInt(userStringElements[4]));
                            socketClientList.get(socket).setCurrentchatid(currentUser.getCurrentchatid());
                            socketClientList.get(socket).getCurrentchatid();
                        }
                        else{
                            String[] messageString = outputString.split(":",5);
                            String[] userStringElements = messageString[0].split(",");
                            User currentUser = new User(userStringElements[1],userStringElements[2],userStringElements[3],Integer.parseInt(userStringElements[4]));
                            socketClientList.get(socket).setCurrentchatid(currentUser.getCurrentchatid());
                            socketClientList.get(socket).getCurrentchatid();
                            PrintWriter printWriter2 = new PrintWriter(socket.getOutputStream(), true);
                            printWriter2.println("created");
                        }
                        break;
                    case "4":
                        break;
                    default:
                        if (socketClientList.containsKey(socket)) {
                            showMessageToAllClients(socketClientList.get(this.socket),outputString);
                        } else {
                            String[] messageString2 = outputString.split(":",5);
                            String[] userStringElements2 = messageString2[0].split(",");
                            User currentUser2 = new User(userStringElements2[1],userStringElements2[2],userStringElements2[3],Integer.parseInt(userStringElements2[4]));
                            currentUser2.setID(Integer.parseInt(userStringElements2[0]));
                            socketClientList.put(socket, currentUser2);
                            showMessageToAllClients(currentUser2," Arrived!");
                        }
                        break;
                }
            }
        } catch (SocketException e) {
            String printMessage = socketClientList.get(socket) + " left the Server";
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void showMessageToAllClients(User user,String message) {
        Socket socketuser;
        PrintWriter printWriter;
        int i = 0;
        while (i < clients.size()) {
            socketuser = clients.get(i);
            i++;
            try {
                if (socketuser != this.socket && socketClientList.get(socketuser).getCurrentchatid() == user.getCurrentchatid()) {
                    printWriter = new PrintWriter(socketuser.getOutputStream(), true);
                    printWriter.println(user.getName() + message);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}

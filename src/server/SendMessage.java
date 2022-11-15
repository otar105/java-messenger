package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SendMessage extends Thread {

    private DataOutputStream dataOutputStream;
    private Socket user;
    private ArrayList<Socket> allUsers;

    public SendMessage(Socket user,ArrayList<Socket> allUsers) {
        this.user = user;
        this.allUsers = allUsers;
    }

    @Override
    public void run() {
        while (true) {
        String message = "";
            try {
                message = (new DataInputStream(user.getInputStream()).readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }

            String finalMessage = message;
            System.out.println(finalMessage);
            System.out.println(allUsers.size());
            allUsers.forEach(user -> {
                try {
                    dataOutputStream = new DataOutputStream(user.getOutputStream());
                    dataOutputStream.writeUTF(finalMessage);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }
    }
}

package client;
import DB.User;
import server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public static User user;


    public Client(String ip, int port,User user) throws IOException {
        this.socket = new Socket(ip, port);
        this.user = user;
        System.out.println("hi");
        String menu2 = "1 - online users\n2 - my chats\n3 - log out";
        PrintWriter cout = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        ThreadClient threadClient = new ThreadClient(socket);
        new Thread(threadClient).start(); // start thread to receive message
        cout.println(user.toString() + ": has joined the server.");
        String reply = "";
        System.out.println(menu2);
        while (true) {
            System.out.println(">");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                //invite
                int k = 1;
                for (Map.Entry<Socket, User> entry : Server.SocketUserList.entrySet()) {
                    Socket key = entry.getKey();
                    User value = entry.getValue();
                    System.out.println(k + " " + value.getEmail());
                }
                System.out.println("user id: ");
                int userid = scanner.nextInt();
                for (Map.Entry<Socket, User> entry : Server.SocketUserList.entrySet()) {
                    Socket key = entry.getKey();
                    User value = entry.getValue();
                    if (value.getID() == userid) {
                        PrintWriter printWriter;
                        System.out.println("enter chat code: ");
                        int chatid = scanner.nextInt();
                        printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(user.getName() + " has invited you to chat, join by using this code => " + chatid);
                    }
                }
            }
            if (input.equals("2")) {
                int chatid = (int) Math.floor(Math.random()*(1000-1+1)+1);
                user.setCurrentchatid(chatid);
                do {
                    String message = (user.toString() + ": ");
                    reply = scanner.nextLine();
                    if (reply.equals("logout")) {
                        cout.println("logout");
                        break;
                    }
                    cout.println(message + reply);
                } while (!reply.equals("logout"));
            }
            if (input.equals("3")){
                System.out.println("enter chat id: ");
                int chatid = scanner.nextInt();
                user.setCurrentchatid(chatid);
                do {
                    String message = (user.toString() + ": ");
                    reply = scanner.nextLine();
                    if (reply.equals("logout")) {
                        cout.println("logout");
                        break;
                    }
                    cout.println(message + reply);
                } while (!reply.equals("logout"));
            }
            //String message = (user.toString() + ": ");
            //reply = scanner.nextLine();
            //cout.println(message + reply);
        }
    }
}

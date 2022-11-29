package client;
import DB.User;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public static User user;
    public BufferedReader cin;

    public Client(String ip, int port,User user) throws IOException {
        this.socket = new Socket(ip, port);
        this.user = user;
        this.cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
                System.out.println("test");
                //invite
                int k = 1;
                cout.println("1");
                System.out.println("test1");
                String message = cin.readLine();
                System.out.println(message);
                System.out.println("user id: ");
                int userid = scanner.nextInt();

            }
            if (input.equals("2")) {
                int chatid = (int) Math.floor(Math.random()*(1000-1+1)+1);
                System.out.println(chatid);
                user.setCurrentchatid(chatid);
                System.out.println("created");
               while (true) {
                   String message = (user.toString() + ": ");
                   reply = scanner.nextLine();
                   System.out.println(reply);
                   cout.println(message + reply);
               }
            }
            if (input.equals("3")){
                System.out.println("enter chat id: ");
                int chatid = scanner.nextInt();
                user.setCurrentchatid(chatid);
                do {
                    String message = (user.toString() + ": ");
                    String reply1 = scanner.nextLine();
                    if (reply.equals("logout")) {
                        cout.println("logout");
                        break;
                    }
                    cout.println(message + reply1);
                } while (!reply.equals("logout"));
            }
        }
    }
}

package client;
import DB.Chat;
import DB.User;
import Service.ChatUtils;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public static User user;
    public BufferedReader cin;

    public Client(String ip, int port,User user) throws IOException, SQLException {
        this.socket = new Socket(ip, port);
        this.user = user;
        ChatUtils chatUtils = new ChatUtils();
        String menu2 = "1 - online users\n2 - create chat\n3 - join chat";
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
                cout.println(user.toString() + ":1");
            }
            if (input.equals("2")) {
                int chatid = (int) Math.floor(Math.random()*(1000-1+1)+1);
                System.out.println(chatid);
                user.setCurrentchatid(chatid);
                PrintWriter cout2 = new PrintWriter(socket.getOutputStream(), true);
                cout2.println(user.toString() + ":2");
                do {
                    String message2 = (user.toString() + ": ");
                    String reply1 = scanner.nextLine();
                    Chat chat = new Chat(user.getName(),reply1,user.getCurrentchatid());
                    chatUtils.add_message(chat);
                    if (reply.equals("logout")) {
                        cout.println("logout");
                        break;
                    }
                    cout.println(message2 + reply1);
                } while (!reply.equals("logout"));
            }
            if (input.equals("3")){
                System.out.println("enter chat id: ");
                int chatid = scanner.nextInt();
                user.setCurrentchatid(chatid);
                String message = (user.toString() + ":3");
                cout.println(message);
                if (chatUtils.check(chatid) >0) {
                    List<Chat> chats= chatUtils.read(chatid);
                    for (Chat i : chats) {
                        System.out.println(i.getCreatorName()+" : "+i.getMessage());
                    }
                }
                do {
                    String message2 = (user.toString() + ": ");
                    String reply1 = scanner.nextLine();
                    Chat chat = new Chat(user.getName(),reply1,user.getCurrentchatid());
                    chatUtils.add_message(chat);
                    if (reply.equals("logout")) {
                        cout.println("logout");
                        break;
                    }
                    cout.println(message2 + reply1);
                } while (!reply.equals("logout"));
            }
        }
    }
}

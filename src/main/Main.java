package main;

import Service.AuthenticationService;
import DB.User;
import server.Server;
import client.Client;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Server server = new Server(9998);
        Scanner scanner = new Scanner(System.in);
        String menu1 = "1 - login\n2 - register\n3 - menu";
        String menu2 = "1 - online users\n2 - my chats\n3 - log out";
        System.out.println("Welcome to messenger");
        User user = null;
        AuthenticationService authenticationService = new AuthenticationService();
        while(true) {
            System.out.println(">");
            String input = scanner.nextLine();
            if (user == null) {
                System.out.println(menu1);
                if (input.equals("1")) {
                    System.out.println("email:");
                    String email = scanner.nextLine();
                    System.out.println("password:");
                    String password = scanner.nextLine();
                    User temp_user = authenticationService.logIn(email,password);
                    if (authenticationService != null) {
                        user = temp_user;
                        new Client("127.0.0.1", 9998,user);
                    } else {
                        System.out.println("invalid information!");
                    }
                }
                if (input.equals("2")) {
                    System.out.println("email:");
                    String email = scanner.nextLine();
                    System.out.println("username:");
                    String username = scanner.nextLine();
                    System.out.println("password:");
                    String password = scanner.nextLine();
                    String registration_date = LocalDateTime.now().toString();
                    User new_user = new User(email,username,password,registration_date);
                    authenticationService.register(new_user);
                    System.out.println("Successfully registered! you can now login!");
                }
                if (input.equals("3")){
                    System.out.println(menu1);
                }
            }
            else {
                System.out.println(menu2);
                if (input.equals("1")) {

                }
                if (input.equals("2")) {
                    //text
                }
                if (input.equals("3")){
                    System.out.println(menu1);
                }
            }
        }


    }
}
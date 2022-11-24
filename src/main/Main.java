package main;

import Service.AuthenticationService;
import DB.User;
import client.Client;
import server.Server;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        String menu1 = "1 - login\n2 - register\n3 - menu";
        System.out.println("Welcome to messenger");
        User user = null;
        AuthenticationService authenticationService = new AuthenticationService();
        System.out.println(menu1);
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
                    System.out.println(temp_user);
                    if (temp_user != null) {
                        user = temp_user;
                        System.out.println("successfully logged in");
                        Client c = new Client("127.0.0.1",5000,user);
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
                    User new_user = new User(email,username,password,0);
                    authenticationService.register(new_user);
                    System.out.println("Successfully registered! you can now login!");
                }
                if (input.equals("3")){
                    System.out.println(menu1);
                }
            }
        }


    }
}
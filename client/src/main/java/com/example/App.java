package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3000);
        
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        
        while (true) {
            System.out.println("lista comandi:D -> richiesta disponibilità,A ->  acquista biglietto, Q-> disconnessione");
            
            String command = keyboard.readLine();

            if (command.equalsIgnoreCase("Q"))
                break;

            out.println(command);
            String serverResponse = in.readLine();
            System.out.println("Il server dice: " + serverResponse);
        }
        in.close();
        out.close();
        s.close();
    }
}
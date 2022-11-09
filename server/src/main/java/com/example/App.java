package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App{
  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(3000);
    System.out.println("ascolto sulla porta 3000");
    int c = 1;
    int t = 3;
    boolean running = true;
    while (running) {
      Socket s = ss.accept();
      System.out.println("il client è connesso!!");
      ClientHandler client = new ClientHandler(s, t);
      c++;
      client.start();
    }
    ss.close();
  }
}
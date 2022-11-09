package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private static int ticket;

    public ClientHandler(Socket s, int c, int t, String nome) {
        this.s = s;
        ticket=t;
        try {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        out.println("'A' ->  acquista biglietto 'D' -> richiesta disponibilità 'Q' -> disconnessione");
        try {
            while(true){
                String richie = in.readLine();
                if(richie.equals("D")){
                    avaible(out, ticket);
                }else if(richie.equals("A")){
                    ticket = acquista(out, ticket);

                }else if(richie.equals("Q")){
                    out.println("Addio...");
                    s.close();
                }else{
                    out.println( richie + " non trovata");  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void avaible(PrintWriter out, int ticket) throws Exception{
        if(ticket>0){
            out.println("disponibili:"+ticket);
        }else{
            out.println("Biglietti esauriti");
        }
        return;
    }

    public static int acquista(PrintWriter out, int ticket) throws Exception{
        if(ticket>0){
            ticket=ticket-1;
            out.println("Biglietto acquistato");
        }else{
            out.println("Biglietti esauriti");
        }
        return ticket;
    }
}
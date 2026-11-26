package com.xztl.serverarchive;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int porta;

    public Server(int porta) {
        this.porta = porta;
    }

    public void avvio() {

        try (ServerSocket s = new ServerSocket(porta);) {
            System.out.println("server in ascolto sulla porta " + s.getLocalPort());

            while (true) {

                Socket socket = s.accept();

                new Thread(new ClientHandler(socket)).start();

                System.out.println("client connesso");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}









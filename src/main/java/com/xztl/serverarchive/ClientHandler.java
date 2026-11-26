package com.xztl.serverarchive;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    Command cmd = new Command();
    DocumentReader dr = new DocumentReader();
    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        ) {

            String req = in.readLine();
            
            try {
                cmd.insert(req);
                dr.insert(cmd.getCmdPart(cmd.getCmdMaxArgs() - 1));
                
                String res = dr.read();
                out.println("OK FILE\n");
                out.println(res);
                
            } catch (CommandException e) {
                out.println("ERR " + e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getClass() + ": " + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

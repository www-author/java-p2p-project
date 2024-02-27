package com.networking.p2p.domain.peer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadManager extends Thread {
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;

    private static ServerThreadManager instance;

    private ServerThreadManager(Socket socket, ServerThread serverThread) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    public static ServerThreadManager of(
            Socket socket,
            ServerThread serverThread
    ) {
         if (instance == null) {
             return new ServerThreadManager(socket, serverThread);
         }
         return instance;
    }


    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) serverThread.sendMessage(reader.readLine());
        } catch (Exception e) {
            serverThread.getServerThreads().remove(this);
        }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}

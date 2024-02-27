package com.networking.p2p.domain.peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private Set<ServerThreadManager> serverThreads = new HashSet<ServerThreadManager>();
    private static ServerThread instance;

    private ServerThread(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public static ServerThread from(int port) throws IOException {
        if (instance == null) {
            return new ServerThread(port);
        }
        return instance;
    }


    public void run() {
        try {
            while (true) {
                ServerThreadManager serverThreadManager = ServerThreadManager.of(
                        serverSocket.accept(),
                        this
                );
                serverThreads.add(serverThreadManager);
                serverThreadManager.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String message) {
        try {
            for (ServerThreadManager t : serverThreads) {
                t.getPrintWriter().println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<ServerThreadManager> getServerThreads() {
        return  serverThreads;
    }
}


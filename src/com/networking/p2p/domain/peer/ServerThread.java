package com.networking.p2p.domain.peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private Set<ServerThreadManager> serverThreads = new HashSet<ServerThreadManager>();
    public ServerThread(String portNumber) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(portNumber));
    }

    public void run() {
        try {
            while (true) {
                ServerThreadManager serverThreadManager = new ServerThreadManager(serverSocket.accept(), this);
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


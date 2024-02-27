package com.networking.p2p.domain.peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerThread extends Thread {
    private final ServerSocket serverSocket;
    private final Set<ServerThreadManager> serverThreads;
    private static ServerThread instance;

    private ServerThread(int port) throws IOException {
        serverThreads = ConcurrentHashMap.newKeySet();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        for (ServerThreadManager t : serverThreads) {
            t.getPrintWriter().println(message);
        }
    }

    public Set<ServerThreadManager> getServerThreads() {
        return serverThreads;
    }
}


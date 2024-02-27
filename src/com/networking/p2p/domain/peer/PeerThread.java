package com.networking.p2p.domain.peer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerThread extends Thread {
    private final BufferedReader reader;
    private static PeerThread instance;

    private PeerThread(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PeerThread from(Socket socket) throws IOException {
        return new PeerThread(socket);
    }


    public void run() {
        boolean isRun = true;
        do {
            try {
                JsonObject jsonObject = Json.createReader(reader).readObject();
                if (jsonObject.containsKey("username")) {
                    System.out.println("[" + jsonObject.getString("username") + "]: " + jsonObject.getString("message"));
                }
            } catch (Exception e) {
                isRun = false;
                interrupt();
            }
        } while (isRun);
    }
}

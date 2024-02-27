package com.networking.p2p.domain.peer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PeerThread extends Thread {
    private BufferedReader reader;
    public PeerThread(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                JsonObject jsonObject = Json.createReader(reader).readObject();
                if (jsonObject.containsKey("username")) {
                    System.out.println("[" + jsonObject.getString("username") + "]: " + jsonObject.getString("message"));
                }
            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
}

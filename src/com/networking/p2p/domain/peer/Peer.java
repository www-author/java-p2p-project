package com.networking.p2p.domain.peer;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.StringWriter;
import java.net.Socket;

import static com.networking.p2p.global.constants.MessageConstants.*;

public class Peer {
    private Peer() {}

    /*
        Peer클래스가 최초 로딩 시에도 함께 초기화되지 않고 getInstance 메서드에만 호출될 때 인스턴스 생성
        (static 내부 클래스를 이용)
    */
    private static class PeerInstanceHolder {
        private static final Peer INSTANCE = new Peer();
    }

    public static Peer getInstance() {
        return PeerInstanceHolder.INSTANCE;
    }

    public void updateListenToPeers(
            BufferedReader bufferedReader,
            String username,
            ServerThread serverThread) throws Exception {

        System.out.println(INPUT_DESTINATION_INFORMATION_TO_RECEIVE_THE_MESSAGE.getMessage());
        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");

        // 사용자가 s 입력 시 건너뜀
        if(!input.equals("s")) {
            for (int i = 0; i < inputValues.length; i++) {
                String[] address = inputValues[i].split(":");
                Socket socket = null;
                try {
                    socket = new Socket(address[0], Integer.valueOf(address[1]));
                    new PeerThread(socket).start();
                } catch (Exception e) {
                    if(socket != null) socket.close();
                    else System.out.println(INVALID_INPUT_NEXT_STEP.getMessage());
                }
            }
        }
        communicate(bufferedReader, username, serverThread);
    }

    public void communicate(BufferedReader bufferedReader, String username, ServerThread serverThread) throws Exception {
        try {
            System.out.println("> you can now communicate (e to exit, c to change)");
            boolean flag = true;
            while (flag) {
                String message = bufferedReader.readLine();
                if (message.equals("e")) {
                    flag = false;
                    break;
                } else if (message.equals("c")) {
                    updateListenToPeers(bufferedReader, username, serverThread);
                } else {
                    StringWriter stringWriter = new StringWriter();
                    Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
                            .add("username", username)
                            .add("message", message)
                            .build());
                    serverThread.sendMessage(stringWriter.toString());
                }
            }
            System.exit(0);
        } catch (Exception e) {}
    }
}

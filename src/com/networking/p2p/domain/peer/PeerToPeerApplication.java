package com.networking.p2p.domain.peer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.networking.p2p.global.constants.MessageConstants.INPUT_PEER_INFORMATION;

public class PeerToPeerApplication {
    public static void main(String[] args){
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println(INPUT_PEER_INFORMATION.getMessage());
                String[] peerInformation = reader.readLine().split("\\s+");
                int indexOfPort = peerInformation.length - 1;
                ServerThread serverThread = new ServerThread(peerInformation[indexOfPort]);
                serverThread.start(); // server socket open
                Peer.getInstance().updateListenToPeers(
                        reader,
                        peerInformation[indexOfPort],
                        serverThread
                );
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
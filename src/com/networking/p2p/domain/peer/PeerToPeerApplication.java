package com.networking.p2p.domain.peer;

import com.networking.p2p.global.utils.NumberValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.networking.p2p.global.constants.MessageConstants.INPUT_PEER_INFORMATION;

public class PeerToPeerApplication {
    public static void main(String[] args){
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println(INPUT_PEER_INFORMATION.getMessage());
                String[] peerInformation = reader.readLine().split("\\s+");
                int indexOfPort = peerInformation.length - 1;
                NumberValidator.validatePortNumber(peerInformation[indexOfPort]);
                ServerThread serverThread = ServerThread.from(
                        Integer.parseInt(peerInformation[indexOfPort])
                );
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
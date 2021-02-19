package com.aarya.game.networking;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(NetworkConnection.port);
        System.out.println("ServerSocket: " + server);

        List<ServerThread> connections = new ArrayList<>();

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                System.out.println("Client is connected: " + socket);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                System.out.println("Creating thread for client...");

                ServerThread t = new ServerThread(socket, in, out);
                connections.add(t);
                t.start();

                for(ServerThread conn: connections) {
                    conn.write("Total number of connections: " + connections.size());
                }

            } catch (Exception ex) {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }

}

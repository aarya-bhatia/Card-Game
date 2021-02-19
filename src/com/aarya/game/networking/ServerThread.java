package com.aarya.game.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    final Server server;
    final ObjectOutputStream out;
    final ObjectInputStream in;
    final Socket socket;

    volatile boolean running;

    public ServerThread(Server server, Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.server = server;
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public synchronized void setRunning(boolean b) {
        running = b;
    }

    @Override
    public void run() {
        setRunning(true);
        while (running) {
            try {
                String s = (String) in.readObject();
                System.out.println("Message Received: " + s);
                server.broadcast(socket, s);
                if (s.equalsIgnoreCase("EXIT")) {
                    close();
                }
            } catch (Exception e) {
                System.out.println("Exception in server: " + e.getMessage());
                try {
                    close();
                } catch (IOException e2) {
                    break;
                }
            }
        }
    }

    public synchronized void update(Socket client, String message) throws IOException {
        this.out.writeObject("[" + client.getPort() + "]" + " : " + message);
    }

    public void close() throws IOException {
        System.out.println("Terminating connection for client: " + this.socket);
        setRunning(false);
        socket.close();
        this.server.unregister(this);
    }
}


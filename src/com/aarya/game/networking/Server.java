package com.aarya.game.networking;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    ArrayList<ServerThread> conn = new ArrayList<>();

    public Server() throws IOException {
        start();
    }

    public synchronized void register(ServerThread t) throws IOException {
        System.out.println("Registered socket: " + t);
        this.conn.add(t);
        this.broadcast(t.socket, t + " Has joined the server");
    }

    public synchronized void unregister(ServerThread t) throws IOException {
        System.out.println("Unregistered socket: " + t);
        this.conn.remove(t);
        this.broadcast(t.socket, t + " Has left the server");
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(NetworkConnection.port);
        System.out.println("ServerSocket: " + server);

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ServerThread t = new ServerThread(this, socket, in, out);
                register(t);
                t.start();
            } catch (Exception e) {
                break;
            }
        }
    }

    public synchronized void broadcast(Socket socket, String message) throws IOException {
        for(ServerThread t: conn) {
            if(t.socket != socket) {
               t.update(socket, message);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}

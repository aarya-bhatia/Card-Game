package com.aarya.networking;

import java.net.*;
import java.io.*;

public class Server {

    public static final String host = "localhost";
    public static final int port = 4999;

    private final Observable<Object> connectionManager;

    public Server(Observable<Object> connectionManager) throws IOException {
        this.connectionManager = connectionManager;
        start();
    }

    public synchronized void register(Observer<Object> t) throws IOException {
        this.connectionManager.addListener(t);
        this.connectionManager.notifyObservers(t + " Has joined the server");
    }

    public synchronized void unregister(ServerThread t) throws IOException {
        this.connectionManager.removeListener(t);
        this.connectionManager.notifyObservers(t + " Has left the server");
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("ServerSocket: " + server);

        while (true) {
            try {
                Socket socket = server.accept();

                RunnableObserver<Object> t = new ServerThread(this, socket,
                        new ObjectInputStream(socket.getInputStream()),
                        new ObjectOutputStream(socket.getOutputStream())
                );

                this.register(t);
                Server.createThread(t);
            } catch (Exception e) {
                break;
            }
        }
    }

    public static void createThread(RunnableObserver<Object> t) {
        Thread tmp = new Thread(t);
        tmp.start();
    }

    public static void create() throws IOException {
        new Server(new ConnectionManager<>());
    }

    public static void main(String[] args) throws IOException {
        Server.create();
    }

    public Observable<Object> getConnectionManager() {
        return this.connectionManager;
    }
}

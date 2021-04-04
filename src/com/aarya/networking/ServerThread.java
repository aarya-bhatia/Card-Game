package com.aarya.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements RunnableObserver<Object> {

    final Server server;
    final ObjectOutputStream out;
    final ObjectInputStream in;
    final Socket socket;

    private volatile boolean running;

    public ServerThread(Server server, Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.server = server;
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public synchronized void setRunning(boolean b) {
        running = b;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        setRunning(true);
        try {
            while (running) {
                try {
                    accept();
                } catch (Exception e) {
                    System.out.println("Exception in server: " + e.getMessage());
                    close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() throws IOException {
        System.out.println("Terminating connection for client: " + this.socket);
        setRunning(false);
        this.socket.close();
        this.server.unregister(this);
    }

    public void accept() throws IOException, ClassNotFoundException {
        String s = (String) this.in.readObject();
        System.out.println("Message Received: " + s);
        this.server.getConnectionManager().notifyObservers(s);
        if (s.equalsIgnoreCase("EXIT")) {
            close();
        }
    }

    @Override
    public void update(Object data) throws IOException {
        this.out.writeObject(data);
    }
}


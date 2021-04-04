package com.aarya.networking;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    protected final Socket socket;
    protected final ObjectOutputStream out;
    protected final ObjectInputStream in;
    protected final Scanner sc = new Scanner(System.in);

    private volatile boolean running;

    public Client() throws IOException {
        this.socket = new Socket(Server.host, Server.port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.running = true;

        System.out.println("Client: " + this.socket);

        Thread reader = new Thread(new Reader());
        reader.start();

        Thread writer = new Thread(new Writer());
        writer.start();
    }

    private boolean isRunning() {
        return running;
    }

    private synchronized void setRunning(boolean b) {
        running = b;
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }

    private class Reader implements Runnable {

        @Override
        public void run() {
            while (isRunning()) {
                try {
                    String message = (String) in.readObject();
                    System.out.println("New Message: " + message);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Exception in Client Reader");
                    setRunning(false);
                    break;
                }
            }
        }
    }

    private class Writer implements Runnable {

        @Override
        public void run() {
            while (running) {
                String input = sc.nextLine();
                try {
                    out.writeObject(input);

                    if (input.equalsIgnoreCase("EXIT")) {
                        System.out.println("Closing connection");
                        socket.close();
                        running = false;
                    }
                } catch (IOException e) {
                    System.out.println("Exception in client writer");
                    setRunning(false);
                    break;
                }
            }
        }
    }

}


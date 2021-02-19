package com.aarya.game.networking;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

import com.aarya.game.model.Card;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Suit;

public class Client {

    protected final Socket socket;
    protected final ObjectOutputStream out;
    protected final ObjectInputStream in;
    protected final Scanner sc = new Scanner(System.in);

    private volatile boolean running;

    public Client() throws IOException {
        this.socket = new Socket(NetworkConnection.host, NetworkConnection.port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.running = true;

        System.out.println("Client: " + this.socket);

        Thread reader = new Thread(() -> {
            while (isRunning()) {
                try {
                    String message = (String) in.readObject();
                    System.out.println("Message from Server: " + message);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Exception in Client Reader");
                    setRunning(false);
                    break;
                }
            }
        });

        Thread writer = new Thread(() -> {
            while (isRunning()) {
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
        });

        reader.start();
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

}


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

    public Client() throws IOException {
        this.socket = new Socket(NetworkConnection.host, NetworkConnection.port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Client: " + this.socket);
    }

    public void write(Object object) throws IOException {
        out.writeObject(object);
        out.flush();
    }

    public Object read() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void close() throws IOException {
        this.socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = null;
        try (Scanner sc = new Scanner(System.in)) {
            client = new Client();
            while(true) {
                System.out.println("Server: " + client.read());

                String input = sc.nextLine();
                client.write(input);

                if(input.equalsIgnoreCase("EXIT")) {
                    System.out.println("Closing connection");
                    client.close();
                    break;
                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Client Error: " + ex);
        } finally {
            if(client != null) {
                client.close();
            }
        }
    }

}


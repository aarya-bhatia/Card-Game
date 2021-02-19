package com.aarya.game.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    final Socket socket;
    final ObjectOutputStream out;
    final ObjectInputStream in;

    public ServerThread(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public void write(Object object) throws IOException {
        this.out.writeObject(object);
    }

    @Override
    public void run() {
        String s = "";

        while(true) {
            try {
                write("Send message to server (Type EXIT to terminate connection):");

                s = (String) in.readObject();
                if(s.equalsIgnoreCase("EXIT")) {
                    System.out.println("Terminating client: " + this.socket);
                    this.socket.close();
                    // TODO: remove connection from server
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            this.out.close();
            this.in.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

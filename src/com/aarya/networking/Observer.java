package com.aarya.networking;

import java.io.IOException;
import java.net.Socket;

public interface Observer<T> {

    Socket getSocket();

    void update(T data) throws IOException;
}

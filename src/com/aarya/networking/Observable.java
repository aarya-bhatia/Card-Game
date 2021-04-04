package com.aarya.networking;

import java.io.IOException;
import java.net.Socket;

public interface Observable<T> {

    void addListener(Observer<T> o);

    void removeListener(Observer<T> o);

    void notifyObservers(T data) throws IOException;

    void broadcast(Socket socket, T data) throws IOException;
}

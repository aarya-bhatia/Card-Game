package com.aarya.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager<T> implements Observable<T> {

    List<Observer<T>> observers;

    public ConnectionManager() {
        observers = new ArrayList<>();
    }

    @Override
    public void addListener(Observer<T> o) {
        if (!observers.contains(o)) {
            System.out.println("Registered Observer: " + o);
            observers.add(o);
        }
    }

    @Override
    public void removeListener(Observer<T> o) {
        if(observers.remove(o)) {
            System.out.println("Unregistered socket: " + o);
        }
    }

    @Override
    public void notifyObservers(T data) throws IOException {
        for (Observer<T> o : observers) {
            o.update(data);
        }
    }

    @Override
    public void broadcast(Socket source, T data) throws IOException {
        for(Observer<T> o: observers) {
            if(o.getSocket() != source) {
                o.update(data);
            }
        }
    }
}

package com.aarya.game.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class HouseIterator implements Iterator<House> {
    Queue<House> q;

    public HouseIterator(House house) {
        q = new LinkedList<>();
        if (house != null) {
            q.add(house);
        }
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    @Override
    public House next() {
        House current = null;
        if (hasNext()) {
            current = q.poll();
            if(current == null) {
                return next();
            }
            else {
                updateQueue(current);
            }
        }
        return current;
    }

    public void updateQueue(House current) {
        for (House child : current.getChildren()) {
            if (child != null) {
                q.add(child);
            }
        }
    }

}
package com.aarya.networking;

import com.aarya.game.model.Floor;

public class GameNetworkProtocol {

    Floor floor;

    public GameNetworkProtocol(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}

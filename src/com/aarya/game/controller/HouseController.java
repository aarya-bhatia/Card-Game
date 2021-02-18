package com.aarya.game.controller;

import com.aarya.game.model.*;

public class HouseController {

    public static void addChild(House source, House child) throws RankMismatchException {
        source.add(child);
    }

    public static void remove(House house) {
        House.remove(house);
    }

}

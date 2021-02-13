package com.aarya.game.model;

import com.aarya.game.controller.HouseKeyNotFoundException;

//TODO: implement this command
public class ThrowCardCommand extends Command {

    public ThrowCardCommand(CardSelector cardSelector, Player player, Floor floor) {
        super(cardSelector, player, floor);
    }

    @Override
    public void execute() throws RankMismatchException, HouseKeyNotFoundException {

    }

    @Override
    public void undo() {

    }

    @Override
    public boolean validateMove() {
        return false;
    }

    @Override
    public House constructSourceHouse() throws RankMismatchException {
        return null;
    }
}

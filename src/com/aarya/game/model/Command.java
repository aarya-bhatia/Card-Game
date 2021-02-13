package com.aarya.game.model;

import com.aarya.game.controller.HouseKeyNotFoundException;

import java.io.Serializable;

/**
 * Encapsulates the different kinds of commands used in the game. Each command
 * will have access to an instance of the selected cards and houses, the player
 * whose turn it is, and the game floor. This class will support execute and
 * undo operations.
 */
public abstract class Command implements Serializable {

    protected CardSelector cardSelector;
    protected Player player;
    protected Floor floor;

    /* This is the house constructed from the selected elements. */
    protected House source;

    public Command(CardSelector cardSelector, Player player, Floor floor) {
        this.cardSelector = cardSelector;
        this.player = player;
        this.floor = floor;
    }

    public House getSource() {
        return source;
    }

    public void setSource(House source) {
        this.source = source;
    }
    
    public boolean hasSource() { return this.source != null; }

    public CardSelector getCardSelector() {
        return cardSelector;
    }

    public Floor getFloor() {
        return floor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCardSelector(CardSelector cardSelector) {
        this.cardSelector = cardSelector;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void execute() throws RankMismatchException, HouseKeyNotFoundException;

    public abstract void undo();

    public abstract boolean validateMove();

    public abstract House constructSourceHouse() throws RankMismatchException;
}

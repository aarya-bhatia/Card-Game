package com.aarya.game.model;

import java.io.Serializable;

public class Command implements Serializable {

    protected final Player player;
    protected final FloorController floorController;
    protected final CardSelector cardSelector;
    protected CommandState state;
    protected final House source;

    public Command(CardSelector cardSelector, Player player, FloorController floorController)throws IllegalMoveException {
        this.player = player;
        this.cardSelector = cardSelector;
        this.floorController = floorController;
        this.state = CommandState.READY_TO_EXECUTE;
        this.source = constructSourceHouse();
        handleValidation();
    }

    /**
     * All exception handling is done over here
     * @throws IllegalMoveException an illegal move
     */
    public void handleValidation() throws IllegalMoveException {
        if (!cardSelector.hasSelectedCard()) {
            throw new IllegalMoveException("Player must select a card to play");
        }
        if (!PlayerController.hasCard(player, cardSelector.getPlayerCard())) {
            throw new IllegalMoveException("Player does not have card: " + cardSelector.getPlayerCard());
        }
        if(!PlayerController.hasKey(player, cardSelector.getPlayerCard(), source)) {
            throw new IllegalMoveException("Player does not have key for house: " + source);
        }
        for (Card card : cardSelector.getCards()) {
            if (!floorController.hasCard(card)) {
                throw new IllegalMoveException("Floor does not have card: " + card);
            }
        }
        for (House house : cardSelector.getHouses()) {
            if (house.isClosed()) {
                throw new IllegalMoveException("Cannot select house: " + house);
            }
            if (!floorController.hasHouse(house)) {
                throw new IllegalMoveException("Floor does not have house: " + house);
            }
        }
    }

    protected Rank getSourceRank() {
        return Rank.normaliseRank(cardSelector.getCaptureValue());
    }

    public House getSource() {
        return source;
    }

    public CardSelector getCardSelector() {
        return cardSelector;
    }

    public FloorController getFloorController() {
        return floorController;
    }

    public void setCommandState(CommandState state) {
        this.state = state;
    }

    /**
     * @throws IllegalMoveException throws exception if validation fails
     */
    public void execute() throws IllegalMoveException {
        if (!state.equals(CommandState.READY_TO_EXECUTE)) {
            throw new IllegalMoveException("Cannot execute move again");
        }
        setCommandState(CommandState.READY_TO_UNDO);
    }

    /**
     * Undo move
     */
    public void undo() throws IllegalMoveException {
        if (!state.equals(CommandState.READY_TO_UNDO)) {
            throw new IllegalMoveException("Cannot undo move before execution");
        }
        setCommandState(CommandState.READY_TO_EXECUTE);
    }

    /**
     * @return The house build from selected items
     */
    public House constructSourceHouse() {
        return new House(getSourceRank(), cardSelector.getAllCards(), cardSelector.getHouses());
    }
}

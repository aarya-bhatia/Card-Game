package com.aarya.game.model;

public class MergeCommand extends Command {

    public MergeCommand(CardSelector cardSelector, PlayerController playerController, FloorController floorController) throws IllegalMoveException {
        super(cardSelector, playerController, floorController);
    }

    /**
     * The source house is added or merged with another resident on the floor.
     * The player card is taken from the player.
     *
     * @throws IllegalMoveException an illegal move
     */
    @Override
    public void execute() throws IllegalMoveException {
        super.execute();
        getFloorController().performMerge(getSource(), getCardSelector());
        getPlayerController().performMerge(getCardSelector().getPlayerCard());
    }

    /**
     * The move is undone by removing the source house.
     * Then, we restore the state of the floor and player.
     *
     * @throws IllegalMoveException an illegal move
     */
    @Override
    public void undo() throws IllegalMoveException {
        super.undo();
        getFloorController().undoMerge(getSource(), getCardSelector());
        getPlayerController().undoMerge(getCardSelector().getPlayerCard());
    }
}

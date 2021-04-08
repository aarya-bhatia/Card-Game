package com.aarya.game.model;

import com.aarya.game.controller.FloorController;
import com.aarya.game.controller.PlayerController;

public class ClaimCommand extends Command {

    public ClaimCommand(CardSelector cardSelector, Player player, FloorController floorController) throws IllegalMoveException {
        super(cardSelector, player, floorController);
    }

    @Override
    public void handleValidation() throws IllegalMoveException {
       int captureValue = 0;
       for(House house: cardSelector.getHouses()) {
           captureValue += house.getCaptureValue();
       }
       for(Card card: cardSelector.getCards()) {
           captureValue += card.getCaptureValue();
       }
       if(captureValue % cardSelector.getPlayerCard().getCaptureValue() != 0) {
            throw new IllegalMoveException("Capture value mismatch");
       }
    }

    @Override
    public void execute() throws IllegalMoveException {
        super.execute();
        getFloorController().performClaim(getCardSelector());
        PlayerController.performClaim(player, getSource());
    }

    @Override
    public void undo() throws IllegalMoveException {
        super.undo();
        getFloorController().undoClaim(getCardSelector());
        PlayerController.undoClaim(player, getSource());
    }

}

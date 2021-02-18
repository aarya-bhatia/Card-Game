package com.aarya.game.model;

import com.aarya.game.controller.HouseKeyNotFoundException;

import java.util.List;

// TODO: check houses selected are open only
// TODO: check cards and houses from selector exist on the floor

/**
 * The player card, source cards and houses are encapsulated within the
 * cardSelector instance. The floor will accept these and merge them with a
 * house of the same rank as the capture value as the source elements. This
 * should only succeed if the player making the move owns the key to the target
 * house. If the player is using the key itself, he must find a duplicate key
 * before making the move. If their capture value is a valid rank but no house
 * with such rank exists, a new house must be created. Both cards and houses are
 * optional to provide. If no cards or houses are provided, the player card will
 * simply be merged to the house or declared as a new house.
 */
public class MergeCommand extends Command {

    public MergeCommand(CardSelector cardSelector, Player player, Floor floor) throws PlayerCardNotFoundException, RankMismatchException {
        super(cardSelector, player, floor);
    }

    /**
     * The source house is merged with another on the floor.
     * The selected card is taken from player.
     * @throws RankMismatchException should not occur
     * @throws HouseKeyNotFoundException The player does not have a key for source house
     */
    @Override
    public void execute() throws HouseKeyNotFoundException, RankMismatchException {
        super.execute();
        floor.performMerge(source, cardSelector);
        player.performMerge(cardSelector.getSelectedCard());
    }

    /**
     * The move can be undone by removing the source house from its existence. Then
     * we must restore the original cards and houses on the floor and players hand.
     */
    @Override
    public void undo() {
        floor.undoMerge(this.getSource(), cardSelector);
        player.undoMerge(cardSelector.getSelectedCard());
    }

    /**
     * Assures that player has a key to house,
     * which is not equal to the selected card
     * since they might play the key then.
     *
     * @return checks if move is valid for player
     */
    @Override
    public boolean validateMove() {
        return hasSource() && player.hasKey(cardSelector.getSelectedCard(), source.getRank());
    }

    /**
     * Combine all source elements into single house.
     *
     * @throws RankMismatchException capture value of the source elements is invalid
     */
    @Override
    public House constructSourceHouse() throws RankMismatchException {
        Card playerCard = getCardSelector().getSelectedCard(); // necessary
        List<Card> sourceCards = getCardSelector().getCards(); // optional
        List<House> sourceHouses = getCardSelector().getHouses(); // optional

        House source;

        // Find capture value of all elements

        int captureValue = playerCard.getValue();

        if (sourceCards != null) {
            for (Card card : sourceCards) {
                captureValue += card.getValue();
            }
        }

        if (sourceHouses != null) {
            for (House house : sourceHouses) {
                Rank hRank = house.getRank();
                captureValue += hRank.getValue();
            }
        }

        Rank normalisedRank = Rank.normaliseRank(captureValue);

        // Fill the house with elements

        if(!Rank.isValidRank(normalisedRank)) {
            throw new RankMismatchException();
        }

        source = new House(normalisedRank);
        source.addCard(playerCard);

        if (sourceCards != null) {
            source.addCards(sourceCards);
        } else if (sourceHouses != null) {
            for (House house : sourceHouses) {
                source.add(house);
            }
        }

        return source;
    }

}

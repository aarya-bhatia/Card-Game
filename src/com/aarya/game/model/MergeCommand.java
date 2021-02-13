package com.aarya.game.model;

import com.aarya.game.controller.HouseKeyNotFoundException;

import java.util.List;

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

    public MergeCommand(CardSelector cardSelector, Player player, Floor floor) {
        super(cardSelector, player, floor);
    }

    @Override
    public void execute() throws RankMismatchException, HouseKeyNotFoundException {
        /*
         * Note: The source house should not be null since it has at least the player's card.
         * The case for null player card should be handled.
         */
        House source = constructSourceHouse();
        super.setSource(source);

        /* Check to see if player has a key for the source house */
        if(!validateMove()) {
            throw new HouseKeyNotFoundException();
        }

        /* We can proceed with merge now */
        floor.performMerge(source);
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
        Card playerCard = getCardSelector().getSelectedCard();
        List<Card> sourceCards = getCardSelector().getCards();
        List<House> sourceHouses = getCardSelector().getHouses();

        if (playerCard == null) {
            throw new NullPointerException("No card selected by the player");
        }

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
        if (Rank.isValidRank(normalisedRank)) {
            source = new House(normalisedRank);
            source.addCard(playerCard);

            if (sourceCards != null) {
                source.addCards(sourceCards);
            }

            else if (sourceHouses != null) {
                for (House house : sourceHouses) {
                    source.add(house);
                }
            }
        } else {
            throw new RankMismatchException();
        }

        return source;
    }

}

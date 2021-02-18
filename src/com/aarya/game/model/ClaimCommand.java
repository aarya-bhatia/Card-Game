package com.aarya.game.model;

import java.util.List;

public class ClaimCommand extends Command {

    public ClaimCommand(CardSelector cardSelector, Player player, Floor floor) throws RankMismatchException, PlayerCardNotFoundException {
        super(cardSelector, player, floor);
    }

    @Override
    public void execute() throws RankMismatchException, HouseKeyNotFoundException {
        House source = constructSourceHouse();
        setSource(source);

        floor.performClaim(getCardSelector());
        player.performClaim(source);
    }

    @Override
    public void undo() {
        if(hasSource()) {
            floor.undoClaim(getCardSelector());
            player.undoClaim(source);
        }
        else {
            System.out.println("Cannot undo claim because source house does not exist.");
        }
    }

    @Override
    public boolean validateMove() {
        /*
         * The player cannot claim with a card which was being used as a key.
         * If he does so, he would lose ownership of the house.
         */
        return false;
    }

    @Override
    public House constructSourceHouse() throws RankMismatchException {

        Card playerCard = getCardSelector().getPlayerCard();
        List<Card> sourceCards = getCardSelector().getCards();
        List<House> sourceHouses = getCardSelector().getHouses();

        House tmp = new House(playerCard.getRank());

        int value = 0;

        if(sourceCards != null) {
            for(Card card: sourceCards) {
                value += card.getValue();
            }
        }

        if(sourceHouses != null) {
            for(House house: sourceHouses) {
                value += house.getRank().getValue();
            }
        }

        if(tmp.getRank().getValue() % value != 0) {
           throw new RankMismatchException();
        }

        if(sourceCards != null) {
            tmp.addCards(sourceCards);
        }

        if(sourceHouses != null) {
            for(House house: sourceHouses) {
                tmp.add(house);
            }
        }

        return tmp;
    }
}

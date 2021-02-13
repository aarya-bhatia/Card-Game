package com.aarya.game.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.aarya.game.model.*;

public class Controller {
/*
    Floor floor;

    public Controller(Floor floor) {
        this.floor = floor;
    }

    public static enum Operation {
        MERGE, CLAIM
    }

    */
/**
     * This method computes the possible cards the player can claim. The player is
     * allowed to do one of the following: He can claim a card, a combo of cards or
     * a house given the rank of the card he/she is ready to throw.
     * 
     * @param rank - the rank of card player selects
     *//*

    public void canClaim(Rank rank) {
        House house = findHouseWithRank(rank);
        List<Card> cards = findCardsWithRank(rank);
        List<List<Collectible>> combos = findCombosWithRank(rank);
        // List options to player
        System.out.println("You can claim the following:");
        int index = 1;
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(index++ + ". " + cards.get(i).toString());
        }
        System.out.println(index++ + ". " + house.toString());
        for (int i = 0; i < combos.size(); i++) {
            System.out.println(index++ + ". " + "COMBO ->");
            for (Collectible c : combos.get(i)) {
                System.out.println(c.toString());
            }
        }
    }

    private House findHouseWithRank(Rank r) {
        Iterator<House> iter = this.floor.getHouses().iterator();
        while (iter.hasNext()) {
            House h = iter.next();
            if (h.getRank().equals(r)) {
                return h;
            }
        }
        return null;
    }

    private List<Card> findCardsWithRank(Rank r) {
        Iterator<Card> iter = this.floor.getCards().iterator();
        List<Card> found = new ArrayList<Card>();
        while (iter.hasNext()) {
            Card c = iter.next();
            if (c.getRank().equals(r)) {
                found.add(c);
            }
        }
        return found;
    }

    private List<List<Collectible>> findCombosWithRank(Rank r) {
        List<List<Collectible>> found = new ArrayList<>();
        List<Collectible> temp = new ArrayList<>();
        List<Collectible> collectibles = new ArrayList<>(this.floor.getCards());
        List<Collectible> collectibleHouses = new ArrayList<>(this.floor.getUnconcreteHouses());

        collectibles.addAll(collectibleHouses);

        findCardCombosWithRankUtil(found, collectibles, temp, r.getValue(), 0);
        return found;
    }

    */
/**
     * Method to find all subsequence of cards which have a total rank i.e sum equal
     * to the rank of the given card
     * 
     * @param
     *//*

    private void findCardCombosWithRankUtil(List<List<Collectible>> found, List<Collectible> cards,
            List<Collectible> temp, int sum, int index) {

        // no solution
        if (index > cards.size() || sum < 0)
            return;

        // found a possible sequence
        if (sum == 0) {
            // List<Card> cardSeq = new ArrayList<>();
            // for(Collectible collectible: temp) {
            // if(collectible instanceof Card) {
            // cardSeq.add((Card) collectible);
            // }
            // else if(collectible instanceof House) {
            // House tmpHouse = (House) collectible;
            // for(Card c: tmpHouse.getCards()) {
            // cardSeq.add(c);
            // }
            // }
            // }
            found.add(new ArrayList<Collectible>(temp));
            return;
        }

        // for each card we can either include it or not
        else {
            for (int i = index; i < cards.size(); i++) {
                Collectible current = (Collectible) cards.get(i);

                // add the next element to temp
                temp.add(current);

                // recursive call
                findCardCombosWithRankUtil(found, cards, temp, sum - current.getRank().getValue(), index + 1);

                // remove last element from temp
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean containsHouseOfRank(Rank rank) {
        for (House house : this.floor.getHouses()) {
            return house.getRank().equals(rank);
        }
        return false;
    }

    public House createHouseOfRank(Rank rank) {
        House house = new House(rank);
        this.floor.addHouse(house);
        return house;
    }

    public House getHouseOfRank(Rank rank) {
        for (House house : this.floor.getHouses()) {
            if (house.getRank().equals(rank)) {
                return house;
            }
        }
        return null;
    }
*/
}

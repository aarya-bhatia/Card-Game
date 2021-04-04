package com.aarya.test.model;

import org.junit.Test;

import static org.junit.Assert.*;

import com.aarya.game.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class FloorTest {

    private static final Card c1 = new Card(Rank.ACE, Suit.DIAMOND);
    private static final Card c3 = new Card(Rank.THREE, Suit.HEART);
    private static final Card c4 = new Card(Rank.FOUR, Suit.CLUB);
    private static final Card c7 = new Card(Rank.SEVEN, Suit.CLUB);
    private static final Card c11 = new Card(Rank.JACK, Suit.SPADE);
    private static final Card c12 = new Card(Rank.QUEEN, Suit.SPADE);
    private static final Card c9 = new Card(Rank.NINE, Suit.SPADE);

    private static final Floor floor;
    private static final FloorController floorController;

    static {
        floor = new Floor();
        floor.setCards(new ArrayList<>(Arrays.asList(c1, c4, c9, c11)));
        floorController = new FloorController(floor);
    }

    public static boolean validateFloorState(Card[] cards, House[] houses) {
        if (floor.getCards().size() != cards.length
                && floor.getHouses().size() != houses.length) {
            return false;
        }

        for (Card card : cards) {
            if (!floorController.hasCard(card)) {
                return false;
            }
        }

        for (House house : houses) {
            if (!floorController.hasHouse(house)) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void floorTest() {
        assertTrue("Validating floor state", validateFloorState(new Card[]{c1, c4, c9, c11}, new House[]{}));

        CardSelector cardSelector = buildCardSelector(new Card[]{c4}, new House[]{}, c7);
        House h11 = mergeItems(Rank.JACK, cardSelector);

        assertTrue("Validating floor state", validateFloorState(new Card[]{c1, c9, c11}, new House[]{h11}));
        assertSame("Should find the house", floorController.findHouse(Rank.JACK), h11);

        CardSelector cardSelector1 = buildCardSelector(new Card[]{c1}, new House[]{h11}, c12);
        House h12 = mergeItems(Rank.QUEEN, cardSelector1);

        assertSame("Should find house", floorController.findHouse(Rank.QUEEN), h12);
        assertTrue("Validating floor state", validateFloorState(new Card[]{c9, c11}, new House[]{h12}));

        CardSelector cardSelector2 = buildCardSelector(new Card[]{c9}, new House[]{}, c3);
        House h$12 = mergeItems(Rank.QUEEN, cardSelector2);

        assertSame("Should find the house", floorController.findHouse(Rank.QUEEN), h12);
        assertTrue("Validating floor state", validateFloorState(new Card[]{c11}, new House[]{h12}));

//        floorController.undoMerge(h$12, cardSelector2);
//        floorController.undoMerge(h12, cardSelector1);
//        floorController.undoMerge(h11, cardSelector);
//
//        assertTrue("Validating floor state", validateFloorState(new Card[]{c1, c4, c9, c11}, new House[]{}));
    }

    public static CardSelector buildCardSelector(Card[] cards, House[] houses, Card playerCard) {
        CardSelector c = new CardSelector();

        for (Card card : cards) {
            c.select(card);
        }

        for (House house : houses) {
            c.select(house);
        }

        c.setPlayerCard(playerCard);

        return c;
    }

    public static House mergeItems(Rank rank, CardSelector cardSelector) {
        House h = new House(rank, cardSelector.getAllCards(), cardSelector.getHouses()); // The source house
        floorController.performMerge(h, cardSelector);
        return h;
    }
}

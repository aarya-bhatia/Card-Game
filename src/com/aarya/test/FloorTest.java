package com.aarya.test;

import org.junit.Test;

import static org.junit.Assert.*;

import com.aarya.game.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FloorTest {
    private static final House h11 = new House(Rank.JACK);

    private static final Card c1 = new Card(Rank.ACE, Suit.DIAMOND);
    private static final Card c3 = new Card(Rank.THREE, Suit.HEART);
    private static final Card c4 = new Card(Rank.FOUR, Suit.CLUB);
    private static final Card c7 = new Card(Rank.SEVEN, Suit.CLUB);
    private static final Card c11 = new Card(Rank.JACK, Suit.SPADE);
    private static final Card c12 = new Card(Rank.QUEEN, Suit.SPADE);
    private static final Card c9 = new Card(Rank.NINE, Suit.SPADE);


    @Test
    public void floorTest() throws RankMismatchException, RankNotFoundException {
        Floor floor = new Floor();
        floor.setCards(new ArrayList<>(Arrays.asList(c1, c4, c9, c11)));

        assertNotNull("Floor should initialise houses", floor.getHouses());
        assertNotNull("Floor should initialise cards", floor.getCards());
        assertEquals("Floor should contain 4 cards", floor.numCards(), 4);
        assertEquals("Floor should not contain houses", floor.numHouses(), 0);

        assertTrue("Floor should update cards", floor.getCards().contains(c1));
        assertTrue("Floor should update cards", floor.getCards().contains(c4));
        assertTrue("Floor should update cards", floor.getCards().contains(c9));
        assertTrue("Floor should update cards", floor.getCards().contains(c11));

        CardSelector cardSelector = new CardSelector();

        cardSelector.select(c4);
        cardSelector.setPlayerCard(c7); // Assume player has c7

        h11.setCards(new ArrayList<>(Arrays.asList(c4, c7)));

        // creating house jack
        floor.performMerge(h11, cardSelector);

        assertEquals("Floor should update cards", floor.numCards(), 3);
        assertEquals("Floor should update houses", floor.numHouses(), 1);
        assertSame("Floor should update houses", floor.getHouses().get(0), h11);
        assertTrue("Floor should update cards", floor.getCards().contains(c1));
        assertTrue("Floor should update cards", floor.getCards().contains(c9));
        assertTrue("Floor should update cards", floor.getCards().contains(c11));
        assertSame("Should find the house", floor.findHouseWithRank(Rank.JACK), h11);

        CardSelector cardSelector1 = new CardSelector();

        cardSelector1.select(h11);
        cardSelector1.select(c1);
        cardSelector1.setPlayerCard(c12); // Assume player has c12

        House h12 = new House(Rank.QUEEN, new ArrayList<>(Arrays.asList(c1, c12)), new ArrayList<>(Collections.singletonList(h11)));

        // creating house queen
        floor.performMerge(h12, cardSelector1);

        assertEquals("Floor should update cards", floor.numCards(), 2);
        assertEquals("Floor should update houses", floor.numHouses(), 1);
        assertSame("Floor should update houses", floor.getHouses().get(0), h12);
        assertTrue("Floor should update cards", floor.getCards().contains(c9));
        assertTrue("Floor should update cards", floor.getCards().contains(c11));
        assertSame("Should find the house", floor.findHouseWithRank(Rank.QUEEN), h12);

        CardSelector cardSelector2 = new CardSelector();

        cardSelector2.select(c9);
        cardSelector2.setPlayerCard(c3);

        House h$12 = new House(Rank.QUEEN, new ArrayList<>(Arrays.asList(c3, c9)), new ArrayList<>());

        // merging to house queen
        floor.performMerge(h$12, cardSelector2);

        assertEquals("Floor should update cards", floor.numCards(), 1);
        assertEquals("Floor should update houses", floor.numHouses(), 1);
        assertSame("Floor should update houses", floor.getHouses().get(0), h12);
        assertTrue("Floor should update cards", floor.getCards().contains(c11));
        assertSame("Should find the house", floor.findHouseWithRank(Rank.QUEEN), h12);

        floor.undoMerge(h$12, cardSelector2);
        floor.undoMerge(h12, cardSelector1);
        floor.undoMerge(h11, cardSelector);

        // Floor should contains initial cards and no houses

        assertTrue("Floor should update houses", floor.getHouses().isEmpty());
        assertTrue("Floor should update cards", floor.getCards().contains(c1));
        assertTrue("Floor should update cards", floor.getCards().contains(c4));
        assertTrue("Floor should update cards", floor.getCards().contains(c9));
        assertTrue("Floor should update cards", floor.getCards().contains(c11));
    }

}

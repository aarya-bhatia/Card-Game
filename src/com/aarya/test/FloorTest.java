package com.aarya.test;

import org.junit.Test;

import static org.junit.Assert.*;

import com.aarya.game.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloorTest {

    private static House houseJack;
    private static House houseKing1;
    private static House houseKing2;

    public FloorTest() throws RankMismatchException {
        createHouseJack();
        createHouseKing1();
        createHouseKing2();
    }

    public static void createHouseJack() throws RankMismatchException {
        houseJack = new House(Rank.JACK);
        houseJack.addCard(new Card(Rank.JACK, Suit.CLUB));

        House h1 = new House(Rank.JACK);
        h1.addCard(new Card(Rank.JACK, Suit.SPADE));

        House h2 = new House(Rank.JACK);

        houseJack.add(h1);
        houseJack.add(h2);
    }

    public static void createHouseKing1() throws RankMismatchException {
        houseKing1 = new House(Rank.KING);
        houseKing1.addCards(Arrays.asList(
                new Card(Rank.QUEEN, Suit.SPADE),
                new Card(Rank.ACE, Suit.DIAMOND)
        ));
    }

    public static void createHouseKing2() throws RankMismatchException {
        houseKing2 = new House(Rank.KING);

        houseKing2.addCards(Arrays.asList(
                new Card(Rank.EIGHT, Suit.SPADE),
                new Card(Rank.FIVE, Suit.DIAMOND)
        ));

    }

    @Test
    public void floorTest() throws RankMismatchException {
        Floor floor = new Floor();

        List<Card> initStateCards = Arrays.asList(
                new Card(Rank.SEVEN, Suit.CLUB),
                new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.JACK, Suit.SPADE),
                new Card(Rank.FOUR, Suit.CLUB)
        );

        CardSelector cardSelector  = new CardSelector();
        Card playerCard = new Card(Rank.THREE, Suit.HEART);

        cardSelector.select(initStateCards.get(0));
        cardSelector.select(initStateCards.get(1));
        cardSelector.setSelectedCard(playerCard);

        floor.setCards(initStateCards);

        assertNotNull("Floor should initialise houses", floor.getHouses());
        assertNotNull("Floor should initialise cards", floor.getCards());
        assertEquals("Floor should contain 4 cards", floor.numCards(), 4);
        assertEquals("Floor should not contain houses", floor.numHouses(), 0);

        floor.performMerge(cardSelector);

//        assertSame("Should find the house", floor.findHouseWithRank(Rank.JACK), houseJack);

        floor.performMerge(houseKing1);

        assertEquals("Should merge house king as new house", floor.getHouses().size(), 2);
        assertSame("Should find the house merged recently", floor.findHouseWithRank(Rank.KING), houseKing1);

        floor.performMerge(houseKing2);

        // House king2 should be merged with house king1
        // That is, floor should now contain houses jack and king1

        assertEquals(floor.getHouses().size(), 2);
        assertSame(floor.findHouseWithRank(Rank.KING), houseKing1);

        floor.show();

        floor.undoMerge(houseKing2);
    }

}

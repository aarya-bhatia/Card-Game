package com.aarya.test.model;

import com.aarya.game.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardSelectorTest {

    @Test
    public void cardSelectionTest() {

        List<Card> cards = Arrays.asList(
                new Card(Rank.FIVE, Suit.DIAMOND),
                new Card(Rank.SIX, Suit.HEART),
                new Card(Rank.SEVEN, Suit.SPADE),
                new Card(Rank.EIGHT, Suit.CLUB)
        );

        CardSelector cardSelector = new CardSelector();

        cardSelector.select(cards.get(0));
        cardSelector.select(cards.get(1));
        cardSelector.deselect(cards.get(1));
        cardSelector.select(cards.get(2));

        Card selectedCard = new Card(Rank.ACE, Suit.DIAMOND);

        cardSelector.setPlayerCard(selectedCard);

        assertNotNull(cardSelector);
        assertTrue(cardSelector.hasCards());
        assertFalse(cardSelector.hasHouses());
        assertTrue(cardSelector.hasSelectedCard());

        assertEquals(cardSelector.numCards(), 2);
        assertEquals(cardSelector.numHouses(), 0);

        assertEquals(cardSelector.getCards(), Arrays.asList(cards.get(0), cards.get(2)));
        assertSame(cardSelector.getPlayerCard(), selectedCard);

        House house = new House(Rank.JACK, new ArrayList<>(Collections.singletonList(new Card(Rank.JACK, Suit.DIAMOND))), new ArrayList<>());

        cardSelector.deselect(house);
        cardSelector.select(house);

        assertTrue(cardSelector.hasHouses());
        assertEquals(cardSelector.numHouses(), 1);
        assertSame(cardSelector.getHouses().get(0), house);
    }

}

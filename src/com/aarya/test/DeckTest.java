package com.aarya.test;

import com.aarya.game.model.Card;
import com.aarya.game.model.Deck;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void createDeckTest() {
        assertNotNull("Deck should not be null", deck);
        assertNotNull("Cards should not be null", deck.getDeck());
        assertEquals("There should be 52 cards", deck.size(), 52);
    }

    @Test
    public void drawCardTest() {
        Card card = deck.drawCard();
        assertNotNull("should draw a card", card);
        assertEquals("current deck size should be 51", deck.size(), 51);

        Card card2 = deck.drawCard();

        assertNotNull("should draw a card", card2);
        assertEquals("current deck size should be 50", deck.size(), 50);

        assertNotEquals("both cards should be different", card, card2);

        List<Card> cards = deck.getDeck();

        cards.add(card);
        cards.add(card2);

        Collections.shuffle(cards);

        deck.setDeck(cards);

        assertEquals("current deck size should be 52", deck.size(), 52);
    }

    @Test
    public void showCardTest() {
        deck.show();
    }
}

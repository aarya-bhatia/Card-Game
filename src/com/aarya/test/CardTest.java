package com.aarya.test;

import com.aarya.game.model.Card;
import com.aarya.game.model.Rank;
import com.aarya.game.model.Suit;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class CardTest {

    public static Card ACE_OF_SPADE = new Card(Rank.ACE, Suit.SPADE);
    public static Card ACE_OF_CLUB = new Card(Rank.ACE, Suit.CLUB);
    public static Card TWO_OF_HEART = new Card(Rank.TWO, Suit.HEART);
    public static Card TEN_OF_DIAMOND = new Card(Rank.TEN, Suit.DIAMOND);
    public static Card KING_OF_SPADE = new Card(Rank.KING, Suit.SPADE);
    public static Card QUEEN_OF_SPADE = new Card(Rank.QUEEN, Suit.SPADE);
    public static Card JACK_OF_SPADE = new Card(Rank.JACK, Suit.SPADE);
    public static Card SEVEN_OF_SPADE = new Card(Rank.SEVEN, Suit.SPADE);

    public CardTest() {
    }

    @Test
    public void createCardTest() {
        Rank rank = Rank.ACE;
        Suit suit = Suit.SPADE;
        Card c = new Card(rank, suit);
        assertNotNull(c);
        assertEquals(c.getRank(), rank);
        assertEquals(c.getSuit(), suit);
        assertEquals(c.getValue(), rank.getValue());
    }

    @Test
    public void cardPointsTest() {
        assertEquals("Aces are worth one point", 1, ACE_OF_SPADE.getPoints());
        assertEquals("Aces are worth one point", 1, ACE_OF_CLUB.getPoints());
        assertEquals("Clubs are not worth points", 0, TWO_OF_HEART.getPoints());
        assertEquals("Ten of diamonds is worth two point", 2, TEN_OF_DIAMOND.getPoints());
        assertEquals("Spades are worth points equal to capture value", 13, KING_OF_SPADE.getPoints());
        assertEquals("Spades are worth points equal to capture value", 12, QUEEN_OF_SPADE.getPoints());
        assertEquals("Spades are worth points equal to capture value", 11, JACK_OF_SPADE.getPoints());
        assertEquals("Spades are worth points equal to capture value", 7, SEVEN_OF_SPADE.getPoints());
    }

    @Test
    public void compareCardsTest() {
        assertTrue(KING_OF_SPADE.compareTo(QUEEN_OF_SPADE) > 0);
        assertTrue(SEVEN_OF_SPADE.compareTo(QUEEN_OF_SPADE) < 0);
        assertEquals(0, ACE_OF_CLUB.compareTo(ACE_OF_SPADE));
        assertTrue(TWO_OF_HEART.compareTo(JACK_OF_SPADE) < 0);
    }

    @Test
    public void captureValueTest() {
        Card[] cards = {KING_OF_SPADE, TEN_OF_DIAMOND, ACE_OF_SPADE, ACE_OF_CLUB, TWO_OF_HEART, SEVEN_OF_SPADE, JACK_OF_SPADE};
        int actual = 13 + 10 + 1 + 1 + 2 + 7 + 11;
        assertEquals(Card.getCaptureValue(null), 0);
        assertEquals(Card.getCaptureValue(Collections.emptyList()), 0);
        assertEquals(Card.getCaptureValue(Arrays.asList(cards)), actual);
    }
}

package com.aarya.game.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Card implements Comparable<Card>, Collectible, Serializable {

	private final Rank rank;
	private final Suit suit;

	private static final Logger logger = Logger.getLogger(Card.class.getName());

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
//		logger.log(Level.INFO, "Created Card: " + this.toString());
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return rank.getValue();
	}

	@Override
	public String toString() {
		return this.rank + " " + this.suit.getSymbol();
	}

	@Override
	public int compareTo(Card other) {
		return Integer.compare(this.getPoints(), other.getPoints());
	}

	public void show() {
		System.out.println(this.toString());
	}

	/**
	 * <ul>
	 * <li>Cards of SPADE suit have points equal to their rank</li>
	 * <li>TEN of DIAMOND has 2 points</li>
	 * <li>ACE cards have 1 point each</li>
	 * </ul>
	 * 
	 * @return the number of points of card
	 */
	public int getPoints() {
		if (this.rank.equals(Rank.ACE)) {
			return this.getValue();
		} else if (this.suit.equals(Suit.SPADE)) {
			return this.getValue();
		} else if (this.suit.equals(Suit.DIAMOND) && rank.equals(Rank.TEN)) {
			return 2;
		}
		return 0;
	}

	public static int getCaptureValue(List<Card> cards) {
		if(cards == null) {
			return 0;
		}
		int acc = 0;
		for (Card c : cards) {
			acc += c.getValue();
		}
		return acc;
	}
}

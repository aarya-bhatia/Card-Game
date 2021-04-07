package com.aarya.game.model;

import com.aarya.game.view.CardContainer;
import com.aarya.game.view.CardPane;

import java.io.Serializable;
import java.util.List;

public class Card implements Comparable<Card>, Collectible, Serializable {

	private final CardPane cardPane;

	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.cardPane = new CardPane(getImageURL("Cards","png"));
	}

	public CardPane getCardPane() {
		return this.cardPane;
	}

	public static void loadCards(List<Card> cards, CardContainer container) {
		for(Card card: cards) {
			container.put(card.getCardPane());
		}
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

	/**
	 * @param rootDir The folder under src where images are saved
	 * @param ext image extension, should be .png
	 * @return
	 */
	public String getImageURL(String rootDir, String ext) {
		return ("file:").concat(rootDir)
				.concat("/")
				.concat(rank.getName())
				.concat(suit.getName())
				.concat(ext.startsWith(".") ? ext : "." + ext);
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
	 * Scoring Rules
	 *
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

	public int getCaptureValue() {
		return this.getRank().getValue();
	}

}

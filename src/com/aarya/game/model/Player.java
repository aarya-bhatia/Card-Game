package com.aarya.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Serializable {

	private List<Card> hand;

	private int sweeps = 0;

	//TODO: implement keyBox code
	private HashMap<Card, House> keyBox;

	private House collection;

	public Player() { this.hand = new ArrayList<>(); }

	public Player(List<Card> hand) {
		this.hand = hand;
	}

	public List<Card> getHand() {
		return this.hand;
	}

	public House getCollection() {
		return this.collection;
	}

	public void setCollection(House collection) { this.collection = collection; }

	public int getSweeps() {
		return sweeps;
	}

	public void setSweeps(int sweeps) {
		this.sweeps = sweeps;
	}

	public void setHand(List<Card> cards) {
		this.hand = cards;
	}

	public HashMap<Card, House> getKeyBox() {
		return keyBox;
	}

	public void setKeyBox(HashMap<Card, House> keyBox) {
		this.keyBox = keyBox;
	}

	/**
     *
	 * @return points captured by player
	 */
	public int calculatePoints() {
		if(this.collection == null) {
			return 0;
		}

		int points = 0;

		for (House house : this.collection) {
			points += house.getPoints();
		}

		return points;
	}

	/**
	 * Logs the cards in player's hand.
	 */
	public void show() {
		for (Card card : this.hand) {
			System.out.println("Card: " + card.toString());
		}
		System.out.println("\n");
	}

	/**
	 * The selected card is removed from the player's hand.
	 *
	 * @param selectedCard the card
	 */
	public void performMerge(Card selectedCard) {
		this.hand.remove(selectedCard);
	}

	/**
	 * The selected card is added back to the player's hand.
	 *
	 * @param selectedCard the card
	 */
	public void undoMerge(Card selectedCard) {
		this.hand.add(selectedCard);
	}

	/**
	 * Adds the given house to collection.
	 * If the collection is null, it is set
	 * to the given house.
	 *
	 * @param h the given house
	 */
	public void performClaim(House h) throws RankMismatchException {
		if (this.collection == null) {
			this.setCollection(h);
		} else {
			this.collection.add(h);
		}
	}

	/**
	 * Removes the given house claimed from collection.
	 * If the house is the collection, the collection
	 * should be set to null.
	 *
	 * @param h given house
	 */
	public void undoClaim(House h) {
	    if(h == this.collection) {
			this.setCollection(null);
	    	return;
		}

		House parent = h.getParent();
		if(parent != null) {
			if(parent.removeChild(h)) {
				System.out.println("Success: House was removed from players collection.");
			}
			else {
				System.out.println("Failure: House was not removed from players collection.");
			}
		}

	}

	/**
	 * Checks to see if a player has a card of same rank
	 * as the given rank.
	 * @param selectedCard The card currently selected will be thrown so it cannot be included in search.
	 * @param rank The give rank.
	 * @return true if player has key otherwise false
	 */
	public boolean hasKey(Card selectedCard, Rank rank) {
		for (Card card : this.hand) {
			if (card != selectedCard && card.getRank().equals(rank)) {
				return true;
			}
		}

		return false;
	}
}
